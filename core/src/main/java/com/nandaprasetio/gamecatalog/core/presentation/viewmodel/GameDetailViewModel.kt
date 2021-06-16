package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameDetail
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamedetailusecase.GameDetailUseCase
import com.nandaprasetio.gamecatalog.core.exception.NotFoundException
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDetailItemModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailUseCase: GameDetailUseCase,
    private val savedStateHandle: SavedStateHandle
): ParallelLoadingDataViewModel() {
    companion object {
        const val ARGUMENT_GAME_ID = "argument.GAME_ID"
    }

    init {
        loadGameDetail()
    }

    private fun loadGameDetail() {
        parallelingFetchDataToItem<GameDetail, GameDetailItemModelValue>(
            "game-detail-item", { success, failed ->
                savedStateHandle.get<Int>(ARGUMENT_GAME_ID)?.also {
                    gameDetailUseCase.getGameDetail(
                        it, compositeDisposable, success, failed
                    )
                } ?: failed.accept(NotFoundException())
            }, { GameDetailItemModelValue(it) }
        )
    }

    override fun onRefreshInParallelLoadingData() {
        loadGameDetail()
    }
}