package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import android.widget.TextView
import com.nandaprasetio.gamecatalog.R

class ResultFailedLoadingEpoxyHolder: KotlinEpoxyHolder() {
    val errorTextView by bind<TextView>(R.id.text_view_error)
    val errorDescriptionTextView by bind<TextView>(R.id.text_view_error_description)
}