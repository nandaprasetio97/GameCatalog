package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import android.widget.ImageView
import com.nandaprasetio.gamecatalog.R

class ImageEpoxyHolder: KotlinEpoxyHolder() {
    val imageViewContent by bind<ImageView>(R.id.image_view_content)
}