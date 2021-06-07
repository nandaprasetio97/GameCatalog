package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.genreepoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.CardWithTitleAndImageViewEpoxyHolder
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.ItemEpoxyModel

@EpoxyModelClass
abstract class GenreEpoxyModel: EpoxyModelWithHolder<CardWithTitleAndImageViewEpoxyHolder>(), ItemEpoxyModel {
    @EpoxyAttribute
    var genre: Genre? = null

    private val genreEpoxyModelBinder = GenreEpoxyModelBinder()

    override fun getDefaultLayout(): Int {
        return R.layout.item_card_with_title_and_image_view
    }

    override fun bind(holder: CardWithTitleAndImageViewEpoxyHolder) {
        genreEpoxyModelBinder.bind(genre, holder)
    }
}