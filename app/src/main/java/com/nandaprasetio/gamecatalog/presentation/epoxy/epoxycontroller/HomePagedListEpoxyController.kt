package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDeveloperItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.LoadingEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.SeparatorEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.TitleAndDescriptionEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gamedeveloperepoxymodel.GameDeveloperEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.gameepoxymodel.GameEpoxyModel_
import com.nandaprasetio.gamecatalog.presentation.epoxy.epoxymodel.genreepoxymodel.GenreEpoxyModel_

class HomePagedListEpoxyController(
    context: Context,
    errorProvider: ErrorProvider,
    private val parallelFetchDataResultModelFactory: ParallelFetchDataResultModelFactory
): BasePagedListEpoxyController<BaseModelValue>(context, errorProvider) {
    override fun buildItemModel(currentPosition: Int, item: BaseModelValue?): EpoxyModel<*> {
        return item?.let {
            when (item) {
                is GameItemModelValue -> {
                    GameEpoxyModel_()
                        .id("game-${currentPosition}")
                        .game(item.game)
                        .spanSizeOverride { _, _, _, -> 1 }
                }
                else -> throw IllegalStateException("Item is not suitable.")
            }
        } ?: return LoadingEpoxyModel_()
            .id("loading")
            .spanSizeOverride { _, _, _, -> this.spanCount }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        val newModels: MutableList<EpoxyModel<*>> = mutableListOf()

        epoxyListParameter.parallelFetchDataResultMutableList.forEach { map ->
            parallelFetchDataResultModelFactory.addModel(context, map.key, this.spanCount, map.value, errorProvider)?.also {
                newModels.addAll(it)
            }
        }
        newModels.addAll(
            listOf(
                TitleAndDescriptionEpoxyModel_().id("other-game-title-and-description")
                    .title("Game Lainnya")
                    .description("Lihatlah game lainnya.")
                    .spanSizeOverride { _, _, _ -> this.spanCount },
                SeparatorEpoxyModel_().id("other-game-separator")
                    .spanSizeOverride { _, _, _, -> this.spanCount }
            )
        )
        newModels.addAll(models)
        super.addModels(newModels)
    }
}