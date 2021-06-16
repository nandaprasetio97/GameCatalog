package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import android.os.Build
import android.text.Html
import androidx.core.text.parseAsHtml
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

    @EpoxyAttribute
    var readHtmlIntoDescription: Boolean = false

    override fun getDefaultLayout(): Int {
        return R.layout.item_title_and_description
    }

    override fun bind(holder: TitleAndDescriptionEpoxyHolder) {
        super.bind(holder)
        holder.titleTextView.text = title
        holder.descriptionTextView.text = if (readHtmlIntoDescription) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                description?.parseAsHtml(Html.FROM_HTML_MODE_COMPACT)
            } else {
                description?.parseAsHtml()
            }
        } else {
            description
        }
    }
}