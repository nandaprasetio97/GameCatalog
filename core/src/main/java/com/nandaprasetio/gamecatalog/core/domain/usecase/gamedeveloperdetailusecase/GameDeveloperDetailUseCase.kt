package com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperdetailusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface GameDeveloperDetailUseCase {
    fun getGameBasedGameDeveloperDataSourceFactory(
        slug: String?,
        compositeDisposable: CompositeDisposable,
        gameBasedGameDeveloperPagingDataSourceMutableLiveData: MutableLiveData<GameBasedGameDeveloperPagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>
    ): GameBasedGameDeveloperPagingDataSourceFactory

    fun getGameDeveloperDetail(
        id: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<GameDeveloperDetail>,
        onFailed: Consumer<Throwable>
    )
}