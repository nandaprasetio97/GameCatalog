package com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.GenreRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable

class GenrePagingDataSourceFactory(
    private val genreRepository: GenreRepository,
    private val genrePagingDataSourceMutableLiveData: MutableLiveData<GenrePagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Genre>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, Genre>() {
    override fun create(): DataSource<Int, Genre> {
        return GenrePagingDataSource(
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            genreRepository,
            compositeDisposable
        ).also {
            genrePagingDataSourceMutableLiveData.postValue(it)
        }
    }
}