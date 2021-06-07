package com.nandaprasetio.gamecatalog.core.domain.usecase.gamecreatorusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamecreatorpagingdatasource.GameCreatorPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamecreatorpagingdatasource.GameCreatorPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface GameCreatorUseCase {
    fun getGameCreatorDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        gameCreatorPagingDataSourceMutableLiveData: MutableLiveData<GameCreatorPagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameCreator>>>
    ): GameCreatorPagingDataSourceFactory

    fun getGameCreatorData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<GameCreator>>,
        onFailed: Consumer<Throwable>
    )
}