package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgenrepagingdatasource.GameBasedGenrePagingDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreDetail
import com.nandaprasetio.gamecatalog.core.domain.usecase.genredetailusecase.GenreDetailUseCase
import com.nandaprasetio.gamecatalog.core.exception.NotFoundException
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GenreDetailItemModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreDetailViewModel @Inject constructor(
    private val genreDetailUseCase: GenreDetailUseCase,
    private val savedStateHandle: SavedStateHandle
): PagingDataViewModel<Int, Game, GameBasedGenrePagingDataSource>() {
    companion object {
        const val ARGUMENT_GENRE_ID = "argument.GENRE_ID"
        const val ARGUMENT_GENRE_SLUG = "argument.GENRE_SLUG"
    }

    private val genrePagedListLiveData: LiveData<PagedList<GameItemModelValue>> = LivePagedListBuilder(
        genreDetailUseCase.getGameBasedGenreDataSourceFactory(
            savedStateHandle.get(ARGUMENT_GENRE_SLUG), compositeDisposable, getPagingDataSourceLiveData(),
            networkStatusMutableLiveData, errorFetchResultMutableLiveData
        ).map { GameItemModelValue(it) }, pagedListConfig
    ).build()

    init {
        loadGenreDetail()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>> {
        return genrePagedListLiveData as LiveData<PagedList<BaseModelValue>>
    }

    private fun loadGenreDetail() {
        parallelingFetchDataToItem<GenreDetail, GenreDetailItemModelValue>(
        "genre-detail-item", { success, failed ->
                savedStateHandle.get<Int>(ARGUMENT_GENRE_ID)?.also {
                    genreDetailUseCase.getGenreDetail(
                        it, compositeDisposable, success, failed
                    )
                } ?: failed.accept(NotFoundException())
            }, { GenreDetailItemModelValue(it) }
        )
    }
}