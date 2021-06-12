package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.GameDeveloperDetailViewModel
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.PagingDataViewModel
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.gamedeveloperpagedlistepoxycontroller.GameDeveloperDetailPagedListEpoxyController

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDeveloperDetailPagingRecyclerViewFragment: BasePagingRecyclerViewFragment() {
    companion object {
        const val ARGUMENT_GAME_DEVELOPER_SLUG = "argument.GAME_DEVELOPER_SLUG"
        const val ARGUMENT_GAME_DEVELOPER_ID = "argument.GAME_DEVELOPER_ID"
    }

    private val gameDeveloperDetailViewModel: GameDeveloperDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("Game developer id: ${this.arguments?.get(ARGUMENT_GAME_DEVELOPER_ID)}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getPagingDataViewModel(): PagingDataViewModel<*, *, *> {
        return gameDeveloperDetailViewModel
    }

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListEpoxyController(nonNulledContext: Context): BasePagedListEpoxyController<BaseModelValue> {
        return GameDeveloperDetailPagedListEpoxyController(nonNulledContext, defaultErrorProvider, defaultParallelFetchDataResultModelFactory) as BasePagedListEpoxyController<BaseModelValue>
    }
}