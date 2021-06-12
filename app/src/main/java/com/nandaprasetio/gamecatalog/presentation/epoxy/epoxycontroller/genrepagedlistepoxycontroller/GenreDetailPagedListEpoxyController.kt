package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.genrepagedlistepoxycontroller

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.toImageUrlString
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.CombinationWithItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDeveloperDetailItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GenreDetailItemModelValue
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller.BasePagedListEpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.*
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gameepoxymodel.GameEpoxyModel_

class GenreDetailPagedListEpoxyController(
    context: Context,
    errorProvider: ErrorProvider,
    private val parallelFetchDataResultModelFactory: ParallelFetchDataResultModelFactory
): BasePagedListEpoxyController<GameItemModelValue>(context, errorProvider, false) {
    override fun buildItemModel(currentPosition: Int, item: GameItemModelValue?): EpoxyModel<*> {
        return item?.let {
            GameEpoxyModel_()
                .id("game-${currentPosition}")
                .game(item.game)
                .spanSizeOverride { _, _, _, -> 1 }
        } ?: return LoadingEpoxyModel_()
            .id("loading")
            .spanSizeOverride { _, _, _, -> this.spanCount }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        val epoxyModelMutableList: MutableList<EpoxyModel<*>> = mutableListOf()

        epoxyListParameter.parallelFetchDataResultMutableList.forEach { map ->
            parallelFetchDataResultModelFactory.addModel(context, map.key, this.spanCount, map.value, errorProvider)?.also {
                epoxyModelMutableList.addAll(it)
            } ?: addGenreDetailModelValue(map.key, map.value, epoxyModelMutableList)
        }

        epoxyModelMutableList.addAll(models)
        super.addModels(epoxyModelMutableList)
    }

    private fun addGenreDetailModelValue(key: String?, baseModelValue: BaseModelValue, epoxyModelMutableList: MutableList<EpoxyModel<*>>) {
        when(baseModelValue) {
            is CombinationWithItemModelValue<*> -> {
                when (baseModelValue.fetchDataResult) {
                    is FetchDataResult.Success -> {
                        val successFetchDataResult = baseModelValue.fetchDataResult as FetchDataResult.Success
                        if (successFetchDataResult.value is GenreDetailItemModelValue) {
                            val genreDetailItemModelValue = successFetchDataResult.value as GenreDetailItemModelValue
                            genreDetailItemModelValue.genreDetail.also {
                                epoxyModelMutableList.addAll(
                                    listOf(
                                        ImageEpoxyModel_().id("genre-detail-image")
                                            .image(it.imageBackground?.toImageUrlString())
                                            .spanSizeOverride { _, _, _, -> spanCount },
                                        SeparatorEpoxyModel_().id("genre-detail-separator")
                                            .spanSizeOverride { _, _, _, -> spanCount },
                                        TitleAndDescriptionEpoxyModel_().id("genre-detail-title-and-description")
                                            .title(it.name)
                                            .description("${it.gamesCount} Game${if (it.gamesCount > 1) "s" else ""}")
                                            .spanSizeOverride { _, _, _, -> spanCount },
                                        SeparatorEpoxyModel_().id("genre-detail-title-and-description-separator")
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
                        epoxyModelMutableList.addAll(
                            listOf(
                                ImagePlaceholderEpoxyModel_().id("genre-detail-image-placeholder")
                                    .spanSizeOverride { _, _, _, -> spanCount },
                                SeparatorEpoxyModel_().id("genre-detail-separator")
                                    .spanSizeOverride { _, _, _, -> spanCount },
                                TitleAndDescriptionPlaceholderEpoxyModel_().id("genre-detail-title-and-description")
                                    .spanSizeOverride { _, _, _, -> spanCount }
                            )
                        )
                    }
                }
            }
        }
    }
}