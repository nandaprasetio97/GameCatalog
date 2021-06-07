package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gameepoxymodel

import com.airbnb.epoxy.EpoxyModelClass
import com.nandaprasetio.gamecatalog.R

@EpoxyModelClass
abstract class GameCarouselEpoxyModel: GameEpoxyModel() {
    override fun getDefaultLayout(): Int {
        return R.layout.item_carousel_game
    }
}