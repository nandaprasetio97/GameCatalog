package com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository.GameCreatorRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.GameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.subscribeWithComposeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultGameDeveloperUseCase @Inject constructor(
    private val gameDeveloperRepository: GameDeveloperRepository
): GameDeveloperUseCase {
    override fun getGameDeveloperDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        gameDeveloperPagingDataSourceMutableLiveData: MutableLiveData<GameDeveloperPagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameDeveloper>>>
    ): GameDeveloperPagingDataSourceFactory {
        return GameDeveloperPagingDataSourceFactory(
            gameDeveloperRepository, gameDeveloperPagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getGameDeveloperData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<GameDeveloper>>,
        onFailed: Consumer<Throwable>
    ) {
        gameDeveloperRepository.getGameDeveloperList(page, pageSize)
            .subscribeWithComposeDisposable(compositeDisposable, onSuccess, onFailed)
    }
}