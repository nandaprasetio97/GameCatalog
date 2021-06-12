package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgenrepagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.onResultWithPagingResultAsValueAndIntAsKey
import com.nandaprasetio.gamecatalog.core.ext.wrapSingleWithPreparingComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class GameBasedGenrePagingDataSource(
    private val slug: String?,
    private val networkServiceMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>,
    private val gameRepository: GameRepository,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, Game>() {
    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, Game>) {
        singleWrapperGameRepository(1, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it, null)
        }
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Game>) {}

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Game>) {
        singleWrapperGameRepository(params.key, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it)
        }
    }

    private fun singleWrapperGameRepository(key: Int, requestedLoadSize: Int, onSuccess: Consumer<PagingResult<Game>>) {
        return gameRepository.getGameBasedGenreList(slug, key, requestedLoadSize)
            .wrapSingleWithPreparingComponent(networkServiceMutableLiveData, errorFetchDataResultMutableLiveData)
            .setNetworkStateToLoading()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess)
    }
}