package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.gamepagedlistepoxycontroller

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameItemModelValue
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.LoadingEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gameepoxymodel.GameEpoxyModel_

class GamePagedListEpoxyController(
    context: Context,
    errorProvider: ErrorProvider
): BasePagedListEpoxyController<GameItemModelValue>(context, errorProvider) {
    override fun buildItemModel(currentPosition: Int, item: GameItemModelValue?): EpoxyModel<*> {
        return item?.let {
            GameEpoxyModel_()
                .id("game-${currentPosition}")
                .game(item.game)
                .spanSizeOverride { _, _, _, -> 1 }
        } ?: return LoadingEpoxyModel_()
            .id("loading")
            .spanSizeOverride { _, _, _, -> this.spanCount }
    }
}