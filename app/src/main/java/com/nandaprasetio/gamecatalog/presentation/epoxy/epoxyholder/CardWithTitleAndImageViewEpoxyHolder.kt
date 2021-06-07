package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.nandaprasetio.gamecatalog.R

open class CardWithTitleAndImageViewEpoxyHolder: KotlinEpoxyHolder() {
    val backgroundImageView by bind<ImageView>(R.id.image_view_background)
    val titleTextView by bind<TextView>(R.id.text_view_title)
    val containerCardView by bind<CardView>(R.id.card_view_container)
}