package com.nandaprasetio.gamecatalog.core.domain.usecase.gameusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface GameUseCase {
    fun getGameDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        gamePagingDataSourceMutableLiveData: MutableLiveData<GamePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>
    ): GamePagingDataSourceFactory

    fun getGameData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<Game>>,
        onFailed: Consumer<Throwable>
    )
}