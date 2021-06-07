package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import android.widget.TextView
import com.nandaprasetio.gamecatalog.R

class TitleEpoxyHolder: KotlinEpoxyHolder() {
    val titleTextView by bind<TextView>(R.id.text_view_title)
}