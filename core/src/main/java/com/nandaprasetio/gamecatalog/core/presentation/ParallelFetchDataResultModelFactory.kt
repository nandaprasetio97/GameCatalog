package com.nandaprasetio.gamecatalog.core.presentation

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue

abstract class ParallelFetchDataResultModelFactory {
    abstract fun addModel(context: Context, key: String, spanCount: Int, baseModelValue: BaseModelValue, errorProvider: ErrorProvider): List<EpoxyModel<*>>?
}