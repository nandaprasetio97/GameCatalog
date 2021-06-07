package com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface GameDeveloperUseCase {
    fun getGameDeveloperDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        gameDeveloperPagingDataSourceMutableLiveData: MutableLiveData<GameDeveloperPagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameDeveloper>>>
    ): GameDeveloperPagingDataSourceFactory

    fun getGameDeveloperData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<GameDeveloper>>,
        onFailed: Consumer<Throwable>
    )
}