package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import androidx.paging.PagedList
import com.airbnb.epoxy.EpoxyController
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.PagingDataViewModel
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.ParallelLoadingDataViewModel
import com.nandaprasetio.gamecatalog.databinding.FragmentRecyclerViewBinding
import com.nandaprasetio.gamecatalog.presentation.epoxy.WithListParameterEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BasePagingRecyclerViewFragment: BaseParallelLoadingRecyclerViewFragment() {
    override fun setViewBinding(): FragmentRecyclerViewBinding {
        return FragmentRecyclerViewBinding.inflate(this.layoutInflater)
    }

    override fun getParallelLoadingDataViewModel(): ParallelLoadingDataViewModel {
        return getPagingDataViewModel()
    }

    override fun getWithListParameterEpoxyController(nonNulledContext: Context): WithListParameterEpoxyController {
        return getPagedListEpoxyController(nonNulledContext)
    }

    abstract fun getPagedListEpoxyController(nonNulledContext: Context): BasePagedListEpoxyController<BaseModelValue>

    abstract fun getPagingDataViewModel(): PagingDataViewModel<*, *, *>

    @Suppress("UNCHECKED_CAST")
    override fun onAfterSetParallelFetchDataResultMapObserver(epoxyController: EpoxyController) {
        if (epoxyController is BasePagedListEpoxyController<*>) {
            getPagingDataViewModel().apply {
                this.getPagedListLiveData().observe(viewLifecycleOwner) {
                    (epoxyController as BasePagedListEpoxyController<BaseModelValue>).submitList(it as PagedList<BaseModelValue>)
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onAfterSetParallelFetchDataResultOnFinishedObserver(epoxyController: EpoxyController) {
        val basedPagedListEpoxyController = epoxyController as BasePagedListEpoxyController<BaseModelValue>
        getPagingDataViewModel().apply {
            this.networkStatusLiveData.observe(viewLifecycleOwner) {
                basedPagedListEpoxyController.epoxyListParameter.loading = it <= -1
            }
            this.errorFetchDataResultLiveData.observe(viewLifecycleOwner) {
                basedPagedListEpoxyController.epoxyListParameter.errorFetchDataResult = it
            }
        }
    }
}