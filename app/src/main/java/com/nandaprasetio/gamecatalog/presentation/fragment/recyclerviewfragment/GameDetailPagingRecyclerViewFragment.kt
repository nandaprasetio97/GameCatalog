package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import androidx.fragment.app.viewModels
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.GameDetailViewModel
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.ParallelLoadingDataViewModel
import com.nandaprasetio.gamecatalog.presentation.epoxy.WithListParameterEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.gamedetailepoxycontroller.GameDetailTypedEpoxyController

class GameDetailPagingRecyclerViewFragment: BaseParallelLoadingRecyclerViewFragment() {
    companion object {
        const val ARGUMENT_GAME_ID = "argument.GAME_ID"
    }

    private val gameDetailViewModel: GameDetailViewModel by viewModels()

    override fun getParallelLoadingDataViewModel(): ParallelLoadingDataViewModel {
        return gameDetailViewModel
    }

    override fun getWithListParameterEpoxyController(nonNulledContext: Context): WithListParameterEpoxyController {
        return GameDetailTypedEpoxyController(nonNulledContext, defaultErrorProvider, defaultParallelFetchDataResultModelFactory)
    }
}