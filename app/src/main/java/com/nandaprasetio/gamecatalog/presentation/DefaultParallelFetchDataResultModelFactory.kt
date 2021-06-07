package com.nandaprasetio.gamecatalog.presentation

import android.content.Context
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.carouselmodelvalue.CarouselModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.BaseItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDeveloperItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.PlaceholderEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.ResultFailedLoadingEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.SeparatorEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.TitleAndDescriptionEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gamedeveloperepoxymodel.GameDeveloperCarouselEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gameepoxymodel.GameCarouselEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.genreepoxymodel.GenreCarouselEpoxyModel_

class DefaultParallelFetchDataResultModelFactory: ParallelFetchDataResultModelFactory() {
    override fun addModel(context: Context, key: String, spanCount: Int, baseModelValue: BaseModelValue, errorProvider: ErrorProvider): List<EpoxyModel<*>>? {
        val outputModelList: MutableList<EpoxyModel<*>> = mutableListOf()
        when (baseModelValue) {
            is CarouselModelValue<*> -> {
                provideCarouselModel(context, key, spanCount, outputModelList, baseModelValue, errorProvider)
            }
            else -> return null
        }
        return outputModelList
    }

    private fun provideCarouselModel(
        context: Context, key: String, spanCount: Int,
        outputModelList: MutableList<EpoxyModel<*>>, carouselModelValue: CarouselModelValue<*>, errorProvider: ErrorProvider
    ) {
        when (val fetchDataResult = carouselModelValue.fetchDataResult) {
            is FetchDataResult.Success -> {
                val baseItemModelValueList = fetchDataResult.value
                outputModelList.addAll(
                    listOf(
                        TitleAndDescriptionEpoxyModel_().id("${key}-carousel-title-and-description")
                            .title(carouselModelValue.title)
                            .description(carouselModelValue.description)
                            .spanSizeOverride { _, _, _ -> spanCount },
                        CarouselModel_().id("${key}-carousel")
                            .models(baseItemModelValueList.map { item -> getCarouselItemModel(item) })
                            .padding(Carousel.Padding.dp(16, 0))
                    )
                )
            }
            is FetchDataResult.Error -> {
                outputModelList.add(
                    ResultFailedLoadingEpoxyModel_().id("$key}-result-failed-loading")
                        .errorProviderResult(errorProvider.provideErrorProviderResult(fetchDataResult.t, context))
                        .spanSizeOverride { _, _, _, -> spanCount }
                )
            }
            null -> {
                outputModelList.addAll(
                    listOf(
                        PlaceholderEpoxyModel_().id("${key}-placeholder")
                            .spanSizeOverride { _, _, _, -> spanCount },
                        SeparatorEpoxyModel_().id("${key}-placeholder-separator")
                            .spanSizeOverride { _, _, _, -> spanCount },
                    )
                )
            }
            else -> throw IllegalStateException("Invalid result.")
        }
    }

    private fun getCarouselItemModel(baseItemModelValue: BaseItemModelValue): EpoxyModel<*> {
        return when (baseItemModelValue) {
            is GameItemModelValue -> GameCarouselEpoxyModel_().id("game-${baseItemModelValue.game.id}").game(baseItemModelValue.game)
            is GameDeveloperItemModelValue -> GameDeveloperCarouselEpoxyModel_().id("game-${baseItemModelValue.gameDeveloper.id}").gameDeveloper(baseItemModelValue.gameDeveloper)
            is GenreItemModelValue -> GenreCarouselEpoxyModel_().id("genre-${baseItemModelValue.genre.id}").genre(baseItemModelValue.genre)
            else -> throw IllegalStateException("Invalid item type")
        }
    }
}