package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import androidx.fragment.app.viewModels
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.HomeViewModel
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.PagingDataViewModel
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.HomePagedListEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeRecyclerViewFragment: BaseRecyclerViewFragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun getPagingDataViewModel(): PagingDataViewModel<*, *, *> {
        return homeViewModel
    }

    override fun getPagedListEpoxyController(nonNulledContext: Context): BasePagedListEpoxyController<BaseModelValue> {
        return HomePagedListEpoxyController(nonNulledContext, defaultErrorProvider, defaultParallelFetchDataResultModelFactory)
    }
}