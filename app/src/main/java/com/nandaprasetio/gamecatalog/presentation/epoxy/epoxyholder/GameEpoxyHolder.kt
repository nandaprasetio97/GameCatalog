package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.nandaprasetio.gamecatalog.R

class GameEpoxyHolder: KotlinEpoxyHolder() {
    val gameImageView by bind<ImageView>(R.id.image_view_game)
    val titleTextView by bind<TextView>(R.id.text_view_title)
    val releaseDateTextView by bind<TextView>(R.id.text_view_release_date)
    val containerCardView by bind<CardView>(R.id.card_view_container)
}