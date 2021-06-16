package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperusecase.GameDeveloperUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gameusecase.GameUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.genreusecase.GenreUseCase
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDeveloperItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameUseCase: GameUseCase,
    private val gameDeveloperUseCase: GameDeveloperUseCase,
    private val genreUseCase: GenreUseCase
): PagingDataViewModel<Int, Game, GamePagingDataSource>() {
    private val gameDeveloperPagedListLiveData: LiveData<PagedList<GameItemModelValue>> = LivePagedListBuilder(
        gameUseCase.getGameDataSourceFactory(
            compositeDisposable, getPagingDataSourceLiveData(),
            networkStatusMutableLiveData, errorFetchResultMutableLiveData
        ).map { GameItemModelValue(it) }, pagedListConfig
    ).build()

    init {
        loadGame()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>> {
        return gameDeveloperPagedListLiveData as LiveData<PagedList<BaseModelValue>>
    }

    private fun loadGame() {
        loadGameDeveloperToCarousel()
        loadGenreToCarousel()
    }

    private fun loadGameDeveloperToCarousel() {
        parallelingFetchDataToCarousel<PagingResult<GameDeveloper>, GameDeveloperItemModelValue>(
            "game-developer", "Game Developer", "Lihatlah game developer untuk bagian ini.",
            { success, failed ->
                gameDeveloperUseCase.getGameDeveloperData(1, 10, compositeDisposable, success, failed)
            }, {
                it.result.map { gameDeveloper ->
                    GameDeveloperItemModelValue(gameDeveloper)
                }
            }
        )
    }

    private fun loadGenreToCarousel() {
        parallelingFetchDataToCarousel<PagingResult<Genre>, GenreItemModelValue>(
            "genre", "Genre", "Lihatlah genre untuk bagian ini.",
            { success, failed ->
                genreUseCase.getGenreData(1, 10, compositeDisposable, success, failed)
            }, {
                it.result.map { genre ->
                    GenreItemModelValue(genre)
                }
            }
        )
    }

    override fun onRefreshInPagingData() {
        loadGame()
    }
}