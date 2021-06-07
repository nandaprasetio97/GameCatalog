package com.nandaprasetio.gamecatalog.core.domain.usecase.gamecreatorusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.creatorrolepagingdatasource.CreatorRolePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamecreatorpagingdatasource.GameCreatorPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamecreatorpagingdatasource.GameCreatorPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository.GameCreatorRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.subscribeWithComposeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultGameCreatorUseCase @Inject constructor(
    private val gameCreatorRepository: GameCreatorRepository
): GameCreatorUseCase {
    override fun getGameCreatorDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        gameCreatorPagingDataSourceMutableLiveData: MutableLiveData<GameCreatorPagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameCreator>>>
    ): GameCreatorPagingDataSourceFactory {
        return GameCreatorPagingDataSourceFactory(
            gameCreatorRepository, gameCreatorPagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getGameCreatorData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<GameCreator>>,
        onFailed: Consumer<Throwable>
    ) {
        gameCreatorRepository.getGameCreatorList(page, pageSize)
            .subscribeWithComposeDisposable(compositeDisposable, onSuccess, onFailed)
    }
}