package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.SeparatorEpoxyHolder

@EpoxyModelClass
abstract class SeparatorEpoxyModel: EpoxyModelWithHolder<SeparatorEpoxyHolder>() {
    override fun getDefaultLayout(): Int {
        return R.layout.item_separator
    }
}