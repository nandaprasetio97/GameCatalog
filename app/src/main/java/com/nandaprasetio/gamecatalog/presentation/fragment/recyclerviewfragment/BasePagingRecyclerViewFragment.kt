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

    abstract fun getPagedListEpoxyController(nonNulledContext: Context): BasePagedListEpoxyController<BaseModelValue>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val nonNulledContext = this.context ?: throw IllegalStateException("Context cannot be null.")

        val basePagedListEpoxyController: BasePagedListEpoxyController<BaseModelValue> = getPagedListEpoxyController(
            nonNulledContext
        )
        return viewBinding?.apply {
            this.epoxyRecyclerViewContent.itemAnimator = null
            this.epoxyRecyclerViewContent.setController(basePagedListEpoxyController)
            this.epoxyRecyclerViewContent.layoutManager = GridLayoutManager(nonNulledContext, 2).also {
                it.spanSizeLookup = basePagedListEpoxyController.spanSizeLookup
            }
            this.epoxyRecyclerViewContent.setItemSpacingDp(16)
            this.swipeRefreshLayoutContent.setOnRefreshListener {
                this.swipeRefreshLayoutContent.isRefreshing = false
                getPagingDataViewModel().refresh()
            }
            setLiveDataObserver(basePagedListEpoxyController)
        }?.root
    }

    @Suppress("UNCHECKED_CAST")
    private fun setLiveDataObserver(basedPagedListEpoxyController: BasePagedListEpoxyController<BaseModelValue>) {
        getPagingDataViewModel().apply {
            this.parallelFetchDataResultMapLiveData.observe(viewLifecycleOwner) {
                basedPagedListEpoxyController.epoxyListParameter.parallelFetchDataResultMutableList = it
            }
            this.getPagedListLiveData().observe(viewLifecycleOwner) {
                basedPagedListEpoxyController.submitList(it as PagedList<BaseModelValue>)
            }
            this.parallelFetchDataResultOnFinishedLiveData.observe(viewLifecycleOwner) {
                basedPagedListEpoxyController.requestModelBuild()
            }
            this.networkStatusLiveData.observe(viewLifecycleOwner) {
                basedPagedListEpoxyController.epoxyListParameter.loading = it <= -1
            }
            this.errorFetchDataResultLiveData.observe(viewLifecycleOwner) {
                basedPagedListEpoxyController.epoxyListParameter.errorFetchDataResult = it
            }
        }
    }
}