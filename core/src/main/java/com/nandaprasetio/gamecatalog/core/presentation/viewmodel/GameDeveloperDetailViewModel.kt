package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperdetailusecase.GameDeveloperDetailUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gameusecase.GameUseCase
import com.nandaprasetio.gamecatalog.core.exception.NotFoundException
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDeveloperDetailItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameItemModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDeveloperDetailViewModel @Inject constructor(
    private val gameDeveloperDetailUseCase: GameDeveloperDetailUseCase,
    private val savedStateHandle: SavedStateHandle
): PagingDataViewModel<Int, Game, GameBasedGameDeveloperPagingDataSource>() {
    companion object {
        const val ARGUMENT_GAME_DEVELOPER_ID = "argument.GAME_DEVELOPER_ID"
        const val ARGUMENT_GAME_DEVELOPER_SLUG = "argument.GAME_DEVELOPER_SLUG"
    }

    private val gameDeveloperPagedListLiveData: LiveData<PagedList<GameItemModelValue>> = LivePagedListBuilder(
        gameDeveloperDetailUseCase.getGameBasedGameDeveloperDataSourceFactory(
            savedStateHandle.get(ARGUMENT_GAME_DEVELOPER_SLUG), compositeDisposable, getPagingDataSourceLiveData(),
            networkStatusMutableLiveData, errorFetchResultMutableLiveData
        ).map { GameItemModelValue(it) }, pagedListConfig
    ).build()

    init {
        loadGameDeveloperDetail()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>> {
        return gameDeveloperPagedListLiveData as LiveData<PagedList<BaseModelValue>>
    }

    private fun loadGameDeveloperDetail() {
        parallelingFetchDataToItem<GameDeveloperDetail, GameDeveloperDetailItemModelValue>(
            "game-developer-detail-item", { success, failed ->
                savedStateHandle.get<Int>(ARGUMENT_GAME_DEVELOPER_ID)?.also {
                    gameDeveloperDetailUseCase.getGameDeveloperDetail(
                        it, compositeDisposable, success, failed
                    )
                } ?: failed.accept(NotFoundException())
            }, { GameDeveloperDetailItemModelValue(it) }
        )
    }

    override fun onRefreshInPagingData() {
        loadGameDeveloperDetail()
    }
}