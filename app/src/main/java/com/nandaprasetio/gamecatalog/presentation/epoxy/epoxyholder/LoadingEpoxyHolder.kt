package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.nandaprasetio.gamecatalog.R

class LoadingEpoxyHolder: KotlinEpoxyHolder() {
    val loadingProgressBar by bind<ProgressBar>(R.id.progress_bar_loading)
    val errorLinearLayout by bind<LinearLayoutCompat>(R.id.linear_layout_error)
    val errorTextView by bind<TextView>(R.id.text_view_error)
    val errorDescriptionTextView by bind<TextView>(R.id.text_view_error_description)
}