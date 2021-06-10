package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import androidx.fragment.app.viewModels
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.GenreViewModel
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.PagingDataViewModel
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.genrepagedlistepoxycontroller.GenrePagedListEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenrePagingRecyclerViewFragment: BasePagingRecyclerViewFragment() {
    private val genreViewModel: GenreViewModel by viewModels()

    override fun getPagingDataViewModel(): PagingDataViewModel<*, *, *> {
        return genreViewModel
    }

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListEpoxyController(nonNulledContext: Context): BasePagedListEpoxyController<BaseModelValue> {
        return GenrePagedListEpoxyController(nonNulledContext, defaultErrorProvider) as BasePagedListEpoxyController<BaseModelValue>
    }
}