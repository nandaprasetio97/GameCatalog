package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R

@EpoxyModelClass
abstract class TitleAndDescriptionPlaceholderEpoxyHolder: EpoxyModelWithHolder<ImagePlaceholderEpoxyHolder>() {
    override fun getDefaultLayout(): Int {
        return R.layout.item_placeholder_title_and_description
    }
}