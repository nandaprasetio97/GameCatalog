package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProviderResult
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.LoadingEpoxyHolder

@EpoxyModelClass
abstract class LoadingEpoxyModel: EpoxyModelWithHolder<LoadingEpoxyHolder>() {
    @EpoxyAttribute
    var errorProviderResult: ErrorProviderResult? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_loading
    }

    override fun bind(holder: LoadingEpoxyHolder) {
        super.bind(holder)
        holder.loadingProgressBar.visibility = if (errorProviderResult == null) View.VISIBLE else View.GONE
        holder.errorLinearLayout.visibility = if (errorProviderResult != null) View.VISIBLE else View.GONE
        errorProviderResult?.also {
            holder.errorTextView.text = it.error
            holder.errorDescriptionTextView.text = it.errorDescription
        }
    }
}