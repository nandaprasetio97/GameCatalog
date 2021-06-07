package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import android.widget.TextView
import com.nandaprasetio.gamecatalog.R

class TitleAndDescriptionEpoxyHolder: KotlinEpoxyHolder() {
    val titleTextView by bind<TextView>(R.id.text_view_title)
    val descriptionTextView by bind<TextView>(R.id.text_view_description)
}