package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.fragment.BaseFragment
import com.nandaprasetio.gamecatalog.databinding.FragmentRecyclerViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseRecyclerViewFragment: BaseFragment<FragmentRecyclerViewBinding>() {
    @Inject
    lateinit var defaultErrorProvider: ErrorProvider
    @Inject
    lateinit var defaultParallelFetchDataResultModelFactory: ParallelFetchDataResultModelFactory

    override fun setViewBinding(): FragmentRecyclerViewBinding {
        return FragmentRecyclerViewBinding.inflate(this.layoutInflater)
    }
}