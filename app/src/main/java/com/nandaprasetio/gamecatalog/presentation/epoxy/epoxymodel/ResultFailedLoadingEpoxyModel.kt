package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProviderResult
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxyholder.ResultFailedLoadingEpoxyHolder

@EpoxyModelClass
abstract class ResultFailedLoadingEpoxyModel: EpoxyModelWithHolder<ResultFailedLoadingEpoxyHolder>() {
    @EpoxyAttribute
    var errorProviderResult: ErrorProviderResult? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_result_failed
    }

    override fun bind(holder: ResultFailedLoadingEpoxyHolder) {
        super.bind(holder)
        holder.also {
            it.errorTextView.text = errorProviderResult?.error
            it.errorDescriptionTextView.text = errorProviderResult?.errorDescription
        }
    }
}