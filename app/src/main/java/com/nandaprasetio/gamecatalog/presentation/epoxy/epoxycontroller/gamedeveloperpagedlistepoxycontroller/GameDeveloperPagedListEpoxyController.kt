package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.gamedeveloperpagedlistepoxycontroller

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDeveloperItemModelValue
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.LoadingEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gamedeveloperepoxymodel.GameDeveloperEpoxyModel_

class GameDeveloperPagedListEpoxyController(
    context: Context,
    errorProvider: ErrorProvider
): BasePagedListEpoxyController<GameDeveloperItemModelValue>(context, errorProvider) {
    override fun buildItemModel(currentPosition: Int, item: GameDeveloperItemModelValue?): EpoxyModel<*> {
        return item?.let {
            GameDeveloperEpoxyModel_()
                .id("game-${currentPosition}")
                .gameDeveloper(item.gameDeveloper)
                .spanSizeOverride { _, _, _, -> 1 }
        } ?: return LoadingEpoxyModel_()
            .id("loading")
            .spanSizeOverride { _, _, _, -> this.spanCount }
    }
}