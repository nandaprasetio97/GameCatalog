package com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperdetailusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.GameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.wrapSingle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultGameDeveloperDetailUseCase @Inject constructor(
    private val gameDeveloperRepository: GameDeveloperRepository,
    private val gameRepository: GameRepository
): GameDeveloperDetailUseCase {
    override fun getGameBasedGameDeveloperDataSourceFactory(
        slug: String?, compositeDisposable: CompositeDisposable, gameBasedGameDeveloperPagingDataSourceMutableLiveData: MutableLiveData<GameBasedGameDeveloperPagingDataSource>, networkStatusMutableLiveData: MutableLiveData<Int>, errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>
    ): GameBasedGameDeveloperPagingDataSourceFactory {
        return GameBasedGameDeveloperPagingDataSourceFactory(
            slug, gameRepository, gameBasedGameDeveloperPagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getGameDeveloperDetail(id: Int, compositeDisposable: CompositeDisposable, onSuccess: Consumer<GameDeveloperDetail>, onFailed: Consumer<Throwable>) {
        return gameDeveloperRepository.getGameDeveloperDetail(id)
            .wrapSingle()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess)
    }
}