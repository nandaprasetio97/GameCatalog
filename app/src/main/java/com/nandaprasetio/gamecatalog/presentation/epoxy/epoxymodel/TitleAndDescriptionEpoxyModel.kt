package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.TitleAndDescriptionEpoxyHolder

@EpoxyModelClass
abstract class TitleAndDescriptionEpoxyModel: EpoxyModelWithHolder<TitleAndDescriptionEpoxyHolder>() {
    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    var description: String? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_title_and_description
    }

    override fun bind(holder: TitleAndDescriptionEpoxyHolder) {
        super.bind(holder)
        holder.titleTextView.text = title
        holder.descriptionTextView.text = description
    }
}