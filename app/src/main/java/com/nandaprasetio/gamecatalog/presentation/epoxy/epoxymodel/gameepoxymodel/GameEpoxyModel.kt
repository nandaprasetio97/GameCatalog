package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gameepoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.toImageUrlString
import com.nandaprasetio.gamecatalog.core.ext.setImageUrl
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.GameEpoxyHolder
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.ItemEpoxyModel

@EpoxyModelClass
abstract class GameEpoxyModel: EpoxyModelWithHolder<GameEpoxyHolder>(), ItemEpoxyModel {
    @EpoxyAttribute
    var game: Game? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_game
    }

    override fun bind(holder: GameEpoxyHolder) {
        game?.also {
            holder.titleTextView.text = it.name
            holder.releaseDateTextView.text = it.released
            holder.gameImageView.setImageUrl(it.backgroundImage?.toImageUrlString())
            holder.containerCardView.setOnClickListener {

            }
        }
    }
}