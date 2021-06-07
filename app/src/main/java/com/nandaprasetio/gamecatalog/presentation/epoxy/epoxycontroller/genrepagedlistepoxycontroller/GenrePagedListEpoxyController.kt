package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.genrepagedlistepoxycontroller

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.LoadingEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.genreepoxymodel.GenreEpoxyModel_

class GenrePagedListEpoxyController(
    context: Context,
    errorProvider: ErrorProvider
): BasePagedListEpoxyController<GenreItemModelValue>(context, errorProvider) {
    override fun buildItemModel(currentPosition: Int, item: GenreItemModelValue?): EpoxyModel<*> {
        return item?.let {
            GenreEpoxyModel_()
                .id("genre-${currentPosition}")
                .genre(item.genre)
                .spanSizeOverride { _, _, _, -> 1 }
        } ?: return LoadingEpoxyModel_()
            .id("loading")
            .spanSizeOverride { _, _, _, -> this.spanCount }
    }
}