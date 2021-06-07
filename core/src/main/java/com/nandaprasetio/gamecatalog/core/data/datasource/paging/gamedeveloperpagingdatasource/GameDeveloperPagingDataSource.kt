package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.GameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.onResultWithPagingResultAsValueAndIntAsKey
import com.nandaprasetio.gamecatalog.core.ext.wrapSingleWithPreparingComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class GameDeveloperPagingDataSource(
    private val networkServiceMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameDeveloper>>>,
    private val gameDeveloperRepository: GameDeveloperRepository,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, GameDeveloper>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GameDeveloper>) {
        singleWrapperGameDeveloperRepository(1, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it, null)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GameDeveloper>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GameDeveloper>) {
        singleWrapperGameDeveloperRepository(params.key, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it)
        }
    }

    private fun singleWrapperGameDeveloperRepository(key: Int, requestedLoadSize: Int, onSuccess: Consumer<PagingResult<GameDeveloper>>) {
        return gameDeveloperRepository.getGameDeveloperList(key, requestedLoadSize)
            .wrapSingleWithPreparingComponent(networkServiceMutableLiveData, errorFetchDataResultMutableLiveData)
            .setNetworkStateToLoading()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess)
    }
}