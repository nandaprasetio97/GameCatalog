package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gamedeveloperepoxymodel

import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.toImageUrlString
import com.nandaprasetio.gamecatalog.core.ext.setImageUrl
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.CardWithTitleAndImageViewEpoxyHolder

class GameDeveloperEpoxyModelBinder {
    fun bind(gameDeveloper: GameDeveloper?, cardWithTitleAndImageViewEpoxyHolder: CardWithTitleAndImageViewEpoxyHolder) {
        cardWithTitleAndImageViewEpoxyHolder.apply {
            gameDeveloper?.also {
                this.titleTextView.text = it.name
                this.backgroundImageView.setImageUrl(it.imageBackground?.toImageUrlString())
                this.containerCardView.setOnClickListener {

                }
            }
        }
    }
}