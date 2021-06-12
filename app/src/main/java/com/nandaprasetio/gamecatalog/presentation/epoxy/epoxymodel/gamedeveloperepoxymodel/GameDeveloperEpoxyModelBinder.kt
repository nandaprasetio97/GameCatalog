package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gamedeveloperepoxymodel

import android.content.Intent
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.toImageUrlString
import com.nandaprasetio.gamecatalog.core.ext.setImageUrl
import com.nandaprasetio.gamecatalog.presentation.activity.GameDeveloperDetailActivity
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.CardWithTitleAndImageViewEpoxyHolder

class GameDeveloperEpoxyModelBinder {
    fun bind(gameDeveloper: GameDeveloper?, cardWithTitleAndImageViewEpoxyHolder: CardWithTitleAndImageViewEpoxyHolder) {
        cardWithTitleAndImageViewEpoxyHolder.apply {
            gameDeveloper?.also {
                this.titleTextView.text = it.name
                this.backgroundImageView.setImageUrl(it.imageBackground?.toImageUrlString())
                this.containerCardView.setOnClickListener { view ->
                    view.context.also { context ->
                        Intent(context, GameDeveloperDetailActivity::class.java).also { intent ->
                            intent.putExtra(GameDeveloperDetailActivity.ARGUMENT_GAME_DEVELOPER_SLUG, it.slug)
                            intent.putExtra(GameDeveloperDetailActivity.ARGUMENT_GAME_DEVELOPER_ID, it.id)
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}