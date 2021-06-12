package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.GameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable

class GameBasedGameDeveloperPagingDataSourceFactory(
    private val slug: String?,
    private val gameRepository: GameRepository,
    private val gameBasedGameDeveloperPagingDataSourceMutableLiveData: MutableLiveData<GameBasedGameDeveloperPagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, Game>() {
    override fun create(): DataSource<Int, Game> {
        return GameBasedGameDeveloperPagingDataSource(
            slug,
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            gameRepository,
            compositeDisposable
        ).also {
            gameBasedGameDeveloperPagingDataSourceMutableLiveData.postValue(it)
        }
    }
}