package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgenrepagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable

class GameBasedGenrePagingDataSourceFactory(
    private val slug: String?,
    private val gameRepository: GameRepository,
    private val gameBasedGenrePagingDataSourceMutableLiveData: MutableLiveData<GameBasedGenrePagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, Game>() {
    override fun create(): DataSource<Int, Game> {
        return GameBasedGenrePagingDataSource(
            slug,
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            gameRepository,
            compositeDisposable
        ).also {
            gameBasedGenrePagingDataSourceMutableLiveData.postValue(it)
        }
    }
}