package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.gamedetailepoxycontroller

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.toImageUrlString
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.CombinationWithItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDetailItemModelValue
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BaseEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.*

class GameDetailTypedEpoxyController(
    private val context: Context,
    private val errorProvider: ErrorProvider,
    private val parallelFetchDataResultModelFactory: ParallelFetchDataResultModelFactory
): BaseEpoxyController() {
    override fun buildModels() {
        val newModels: MutableList<EpoxyModel<*>> = mutableListOf()
        epoxyListParameter.parallelFetchDataResultMutableList.forEach { map ->
            parallelFetchDataResultModelFactory.addModel(context, map.key, this.spanCount, map.value, errorProvider)?.also {
                newModels.addAll(newModels)
            } ?: addGameDetailModelValue(map.key, map.value, newModels)
        }
        super.add(newModels)
    }

    private fun addGameDetailModelValue(key: String?, baseModelValue: BaseModelValue, epoxyModelMutableList: MutableList<EpoxyModel<*>>) {
        when(baseModelValue) {
            is CombinationWithItemModelValue<*> -> {
                when (baseModelValue.fetchDataResult) {
                    is FetchDataResult.Success -> {
                        val successFetchDataResult = baseModelValue.fetchDataResult as FetchDataResult.Success
                        if (successFetchDataResult.value is GameDetailItemModelValue) {
                            val gameDetailItemModelValue = successFetchDataResult.value as GameDetailItemModelValue
                            gameDetailItemModelValue.gameDetail.also {
                                epoxyModelMutableList.addAll(
                                    listOf(
                                        ImageEpoxyModel_().id("game-detail-image")
                                            .image(it.backgroundImage?.toImageUrlString())
                                            .spanSizeOverride { _, _, _, -> spanCount },
                                        SeparatorEpoxyModel_().id("game-detail-separator")
                                            .spanSizeOverride { _, _, _, -> spanCount },
                                        TitleAndDescriptionEpoxyModel_().id("game-detail-title-and-description")
                                            .title(it.name)
                                            .description(it.description)
                                            .spanSizeOverride { _, _, _, -> spanCount },
                                        SeparatorEpoxyModel_().id("game-detail-title-and-description-separator")
                                            .spanSizeOverride { _, _, _, -> spanCount },
                                    )
                                )
                            }
                        }
                    }
                    is FetchDataResult.Error -> {
                        val errorFetchDataResult = baseModelValue.fetchDataResult as FetchDataResult.Error
                        epoxyModelMutableList.add(
                            ResultFailedLoadingEpoxyModel_().id("$key}-result-failed-loading")
                                .errorProviderResult(errorProvider.provideErrorProviderResult(errorFetchDataResult.t, context))
                                .spanSizeOverride { _, _, _, -> spanCount }
                        )
                    }
                    null -> {
                        println("Boleh juga hehe")
                        epoxyModelMutableList.addAll(
                            listOf(
                                ImagePlaceholderEpoxyModel_().id("game-detail-image-placeholder")
                                    .spanSizeOverride { _, _, _, -> spanCount },
                                SeparatorEpoxyModel_().id("game-detail-separator")
                                    .spanSizeOverride { _, _, _, -> spanCount },
                                TitleAndDescriptionPlaceholderEpoxyModel_().id("game-detail-title-and-description")
                                    .spanSizeOverride { _, _, _, -> spanCount }
                            )
                        )
                    }
                }
            }
        }
    }
}