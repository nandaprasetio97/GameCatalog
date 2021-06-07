package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.genreepoxymodel

import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.toImageUrlString
import com.nandaprasetio.gamecatalog.core.ext.setImageUrl
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.CardWithTitleAndImageViewEpoxyHolder

class GenreEpoxyModelBinder {
    fun bind(genre: Genre?, cardWithTitleAndImageViewEpoxyHolder: CardWithTitleAndImageViewEpoxyHolder) {
        cardWithTitleAndImageViewEpoxyHolder.apply {
            genre?.also {
                this.titleTextView.text = it.name
                this.backgroundImageView.setImageUrl(it.imageBackground?.toImageUrlString())
                this.containerCardView.setOnClickListener {

                }
            }
        }
    }
}