package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.genreepoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.CardWithTitleAndImageViewCarouselEpoxyHolder
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.ItemEpoxyModel

@EpoxyModelClass
abstract class GenreCarouselEpoxyModel: EpoxyModelWithHolder<CardWithTitleAndImageViewCarouselEpoxyHolder>(), ItemEpoxyModel {
    @EpoxyAttribute
    var genre: Genre? = null

    private val genreEpoxyModelBinder = GenreEpoxyModelBinder()

    override fun getDefaultLayout(): Int {
        return R.layout.item_carousel_card_with_title_and_image_view
    }

    override fun bind(holder: CardWithTitleAndImageViewCarouselEpoxyHolder) {
        genreEpoxyModelBinder.bind(genre, holder)
    }
}