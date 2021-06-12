package com.nandaprasetio.gamecatalog.core.domain.usecase.genredetailusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgenrepagingdatasource.GameBasedGenrePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgenrepagingdatasource.GameBasedGenrePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface GenreDetailUseCase {
    fun getGameBasedGenreDataSourceFactory(
        slug: String?,
        compositeDisposable: CompositeDisposable,
        gameBasedGenrePagingDataSourceMutableLiveData: MutableLiveData<GameBasedGenrePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>
    ): GameBasedGenrePagingDataSourceFactory

    fun getGenreDetail(
        id: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<GenreDetail>,
        onFailed: Consumer<Throwable>
    )
}