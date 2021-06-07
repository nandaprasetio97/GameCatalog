package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gamedeveloperepoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.CardWithTitleAndImageViewCarouselEpoxyHolder
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.ItemEpoxyModel

@EpoxyModelClass
abstract class GameDeveloperCarouselEpoxyModel: EpoxyModelWithHolder<CardWithTitleAndImageViewCarouselEpoxyHolder>(), ItemEpoxyModel {
    @EpoxyAttribute
    var gameDeveloper: GameDeveloper? = null

    private val gameDeveloperEpoxyModelBinder = GameDeveloperEpoxyModelBinder()

    override fun getDefaultLayout(): Int {
        return R.layout.item_carousel_card_with_title_and_image_view
    }

    override fun bind(holder: CardWithTitleAndImageViewCarouselEpoxyHolder) {
        gameDeveloperEpoxyModelBinder.bind(gameDeveloper, holder)
    }
}