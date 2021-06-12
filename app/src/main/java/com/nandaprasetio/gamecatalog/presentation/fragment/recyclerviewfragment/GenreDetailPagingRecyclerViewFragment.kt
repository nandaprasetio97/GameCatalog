package com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.GenreDetailViewModel
import com.nandaprasetio.gamecatalog.core.presentation.viewmodel.PagingDataViewModel
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.gamedeveloperpagedlistepoxycontroller.GameDeveloperDetailPagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.genrepagedlistepoxycontroller.GenreDetailPagedListEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreDetailPagingRecyclerViewFragment: BasePagingRecyclerViewFragment() {
    companion object {
        const val ARGUMENT_GENRE_SLUG = "argument.GENRE_SLUG"
        const val ARGUMENT_GENRE_ID = "argument.GENRE_ID"
    }

    private val genreDetailViewModel: GenreDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("Genre id: ${this.arguments?.get(ARGUMENT_GENRE_ID)}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getPagingDataViewModel(): PagingDataViewModel<*, *, *> {
        return genreDetailViewModel
    }

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListEpoxyController(nonNulledContext: Context): BasePagedListEpoxyController<BaseModelValue> {
        return GenreDetailPagedListEpoxyController(nonNulledContext, defaultErrorProvider, defaultParallelFetchDataResultModelFactory) as BasePagedListEpoxyController<BaseModelValue>
    }
}