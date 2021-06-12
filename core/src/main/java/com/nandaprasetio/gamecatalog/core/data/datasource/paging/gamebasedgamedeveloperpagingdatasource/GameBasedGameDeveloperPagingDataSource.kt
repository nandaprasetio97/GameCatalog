package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.onResultWithPagingResultAsValueAndIntAsKey
import com.nandaprasetio.gamecatalog.core.ext.wrapSingleWithPreparingComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class GameBasedGameDeveloperPagingDataSource(
    private val slug: String?,
    private val networkServiceMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>,
    private val gameRepository: GameRepository,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, Game>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Game>) {
        singleWrapperGameCreatorRepository(1, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it, null)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {
        singleWrapperGameCreatorRepository(params.key, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it)
        }
    }

    private fun singleWrapperGameCreatorRepository(key: Int, requestedLoadSize: Int, onSuccess: Consumer<PagingResult<Game>>) {
        return gameRepository.getGameBasedGameDeveloperList(slug, key, requestedLoadSize)
            .wrapSingleWithPreparingComponent(networkServiceMutableLiveData, errorFetchDataResultMutableLiveData)
            .setNetworkStateToLoading()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess)
    }
}