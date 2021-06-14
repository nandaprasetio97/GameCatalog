package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.presentation.epoxy.EpoxyListParameter
import com.nandaprasetio.gamecatalog.presentation.epoxy.WithListParameterEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.LoadingEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.SeparatorEpoxyModel_

abstract class BasePagedListEpoxyController<T>(
    protected val context: Context,
    protected val errorProvider: ErrorProvider,
    private val includingSeparatorOnTopList: Boolean = true
): PagedListEpoxyController<T>(), WithListParameterEpoxyController {
    override val epoxyListParameter: EpoxyListParameter = EpoxyListParameter { this.requestModelBuild() }

    override fun addModels(models: List<EpoxyModel<*>>) {
        val modelMutableList: MutableList<EpoxyModel<*>> = mutableListOf()
        if (includingSeparatorOnTopList) {
            modelMutableList.add(
                SeparatorEpoxyModel_().id("separator-begin-of-list")
                    .spanSizeOverride { _, _, _ -> this.spanCount }
            )
        }
        modelMutableList.addAll(models)
        when {
            epoxyListParameter.loading -> {
                modelMutableList.add(
                    LoadingEpoxyModel_()
                        .id("loading")
                        .spanSizeOverride { _, _, _, -> this.spanCount }
                        .also {
                            it.errorProviderResult(
                                    epoxyListParameter.errorFetchDataResult?.let { it2 ->
                                    errorProvider.provideErrorProviderResult(it2.t, context)
                                }
                            )
                        }
                )
            }
            else -> {
                modelMutableList.add(
                    SeparatorEpoxyModel_().id("separator-end-of-list")
                        .spanSizeOverride { _, _, _ -> this.spanCount }
                )
            }
        }
        super.addModels(modelMutableList.distinct())
    }
}