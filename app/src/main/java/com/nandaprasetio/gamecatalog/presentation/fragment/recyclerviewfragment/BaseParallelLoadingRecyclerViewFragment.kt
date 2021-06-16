package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyController
import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.fragment.BaseFragment
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.ParallelLoadingDataViewModel
import com.nandaprasetio.gamecatalog.databinding.FragmentRecyclerViewBinding
import com.nandaprasetio.gamecatalog.presentation.epoxy.WithListParameterEpoxyController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseParallelLoadingRecyclerViewFragment: BaseFragment<FragmentRecyclerViewBinding>() {
    @Inject
    lateinit var defaultErrorProvider: ErrorProvider
    @Inject
    lateinit var defaultParallelFetchDataResultModelFactory: ParallelFetchDataResultModelFactory

    override fun setViewBinding(): FragmentRecyclerViewBinding {
        return FragmentRecyclerViewBinding.inflate(this.layoutInflater)
    }

    abstract fun getWithListParameterEpoxyController(nonNulledContext: Context): WithListParameterEpoxyController

    protected abstract fun getParallelLoadingDataViewModel(): ParallelLoadingDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val nonNulledContext = this.context ?: throw IllegalStateException("Context cannot be null.")

        assertIsEpoxyController(getWithListParameterEpoxyController(nonNulledContext)).let { epoxyController ->
            return viewBinding?.apply {
                this.epoxyRecyclerViewContent.itemAnimator = null
                this.epoxyRecyclerViewContent.setController(epoxyController)
                this.epoxyRecyclerViewContent.layoutManager = GridLayoutManager(nonNulledContext, 2).also {
                    it.spanSizeLookup = epoxyController.spanSizeLookup
                }
                this.epoxyRecyclerViewContent.setItemSpacingDp(16)
                this.swipeRefreshLayoutContent.setOnRefreshListener {
                    this.swipeRefreshLayoutContent.isRefreshing = false
                    getParallelLoadingDataViewModel().refresh()
                }
                setLiveDataObserver(epoxyController as WithListParameterEpoxyController, epoxyController)
            }?.root
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setLiveDataObserver(
        withListParameterEpoxyController: WithListParameterEpoxyController,
        epoxyController: EpoxyController
    ) {
        getParallelLoadingDataViewModel().apply {
            this.parallelFetchDataResultMapLiveData.observe(viewLifecycleOwner) {
                withListParameterEpoxyController.epoxyListParameter.parallelFetchDataResultMutableList = it
            }
            onAfterSetParallelFetchDataResultMapObserver(epoxyController)
            this.parallelFetchDataResultOnFinishedLiveData.observe(viewLifecycleOwner) {
                epoxyController.requestModelBuild()
            }
            onAfterSetParallelFetchDataResultOnFinishedObserver(epoxyController)
        }
    }

    private fun assertIsEpoxyController(withListParameterEpoxyController: WithListParameterEpoxyController): EpoxyController {
        if (withListParameterEpoxyController !is EpoxyController) {
            throw IllegalStateException("WithListParameterEpoxyController must be an interface of EpoxyController.")
        }
        return withListParameterEpoxyController
    }

    protected open fun onAfterSetParallelFetchDataResultMapObserver(epoxyController: EpoxyController) {}

    protected open fun onAfterSetParallelFetchDataResultOnFinishedObserver(epoxyController: EpoxyController) {}
}