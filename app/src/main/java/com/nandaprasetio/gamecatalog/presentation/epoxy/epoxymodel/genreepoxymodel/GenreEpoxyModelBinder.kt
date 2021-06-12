package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.genreepoxymodel

import android.content.Intent
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.toImageUrlString
import com.nandaprasetio.gamecatalog.core.ext.setImageUrl
import com.nandaprasetio.gamecatalog.presentation.activity.GameDeveloperDetailActivity
import com.nandaprasetio.gamecatalog.presentation.activity.GenreDetailActivity
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.CardWithTitleAndImageViewEpoxyHolder

class GenreEpoxyModelBinder {
    fun bind(genre: Genre?, cardWithTitleAndImageViewEpoxyHolder: CardWithTitleAndImageViewEpoxyHolder) {
        cardWithTitleAndImageViewEpoxyHolder.apply {
            genre?.also {
                this.titleTextView.text = it.name
                this.backgroundImageView.setImageUrl(it.imageBackground?.toImageUrlString())
                this.containerCardView.setOnClickListener { view ->
                    view.context.also { context ->
                        Intent(context, GenreDetailActivity::class.java).also { intent ->
                            intent.putExtra(GenreDetailActivity.ARGUMENT_GENRE_SLUG, it.slug)
                            intent.putExtra(GenreDetailActivity.ARGUMENT_GENRE_ID, it.id)
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}