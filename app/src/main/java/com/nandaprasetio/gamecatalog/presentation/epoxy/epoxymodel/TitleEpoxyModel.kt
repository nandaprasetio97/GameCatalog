package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.TitleEpoxyHolder

@EpoxyModelClass
abstract class TitleEpoxyModel: EpoxyModelWithHolder<TitleEpoxyHolder>() {
    @EpoxyAttribute
    var title: String = ""

    override fun getDefaultLayout(): Int {
        return R.layout.item_title
    }

    override fun bind(holder: TitleEpoxyHolder) {
        super.bind(holder)
        holder.titleTextView.text = title
    }
}