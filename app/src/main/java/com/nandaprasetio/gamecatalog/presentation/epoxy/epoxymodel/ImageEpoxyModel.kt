package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.ext.setImage
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.ImageEpoxyHolder

@EpoxyModelClass
abstract class ImageEpoxyModel: EpoxyModelWithHolder<ImageEpoxyHolder>() {
    @EpoxyAttribute
    var image: Any? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_image
    }

    override fun bind(holder: ImageEpoxyHolder) {
        super.bind(holder)
        holder.imageViewContent.setImage(image)
    }
}