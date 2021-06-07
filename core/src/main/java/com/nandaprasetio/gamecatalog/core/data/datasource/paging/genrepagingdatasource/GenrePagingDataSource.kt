package com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.GenreRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.onResultWithPagingResultAsValueAndIntAsKey
import com.nandaprasetio.gamecatalog.core.ext.wrapSingleWithPreparingComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class GenrePagingDataSource(
    private val networkServiceMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Genre>>>,
    private val genreRepository: GenreRepository,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, Genre>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Genre>) {
        singleWrapperGameRepository(1, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it, null)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Genre>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Genre>) {
        singleWrapperGameRepository(params.key, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it)
        }
    }

    private fun singleWrapperGameRepository(key: Int, requestedLoadSize: Int, onSuccess: Consumer<PagingResult<Genre>>) {
        return genreRepository.getGenreList(key, requestedLoadSize)
            .wrapSingleWithPreparingComponent(networkServiceMutableLiveData, errorFetchDataResultMutableLiveData)
            .setNetworkStateToLoading()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess)
    }
}