package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.PagingDataViewModel
import com.nandaprasetio.gamecatalog.databinding.FragmentRecyclerViewBinding
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.core.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BasePagingRecyclerViewFragment: BaseFragment<FragmentRecyclerViewBinding>() {
    @Inject
    lateinit var defaultErrorProvider: ErrorProvider
    @Inject
    lateinit var defaultParallelFetchDataResultModelFactory: ParallelFetchDataResultModelFactory

    override fun setViewBinding(): FragmentRecyclerViewBinding {
        return FragmentRecyclerViewBinding.inflate(this.layoutInflater)
    }

    abstract fun getPagingDataViewModel(): PagingDataViewModel<*, *, *>

    @Suppress("UNCHECKED_CAST")
    override fun onAfterSetParallelFetchDataResultMapObserver(epoxyController: EpoxyController) {
        if (epoxyController is BasePagedListEpoxyController<*>) {
            getPagingDataViewModel().apply {
                this.getPagedListLiveData().observe(viewLifecycleOwner) {
                    (epoxyController as BasePagedListEpoxyController<BaseModelValue>).submitList(it as PagedList<BaseModelValue>)
                }
            }
            setLiveDataObserver(basePagedListEpoxyController)
        }?.root
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