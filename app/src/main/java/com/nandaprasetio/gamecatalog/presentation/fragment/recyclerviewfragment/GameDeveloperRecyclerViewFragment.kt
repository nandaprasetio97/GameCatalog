package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import androidx.fragment.app.viewModels
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.GameDeveloperViewModel
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.PagingDataViewModel
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.gamedeveloperpagedlistepoxycontroller.GameDeveloperPagedListEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDeveloperRecyclerViewFragment: BaseRecyclerViewFragment() {
    private val gameDeveloperViewModel: GameDeveloperViewModel by viewModels()

    override fun getPagingDataViewModel(): PagingDataViewModel<*, *, *> {
        return gameDeveloperViewModel
    }

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListEpoxyController(nonNulledContext: Context): BasePagedListEpoxyController<BaseModelValue> {
        return GameDeveloperPagedListEpoxyController(nonNulledContext, defaultErrorProvider) as BasePagedListEpoxyController<BaseModelValue>
    }
}