package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.TitleAndDescriptionEpoxyHolder

@EpoxyModelClass
abstract class TitleAndDescriptionPlaceholderEpoxyModel: EpoxyModelWithHolder<TitleAndDescriptionEpoxyHolder>() {
    override fun getDefaultLayout(): Int {
        return R.layout.item_placeholder_title_and_description
    }
}