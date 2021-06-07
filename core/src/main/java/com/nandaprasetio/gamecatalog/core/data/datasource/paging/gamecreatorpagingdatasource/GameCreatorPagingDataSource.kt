package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamecreatorpagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository.GameCreatorRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.onResultWithPagingResultAsValueAndIntAsKey
import com.nandaprasetio.gamecatalog.core.ext.wrapSingleWithPreparingComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class GameCreatorPagingDataSource(
    private val networkServiceMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameCreator>>>,
    private val gameCreatorRepository: GameCreatorRepository,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, GameCreator>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GameCreator>) {
        singleWrapperGameCreatorRepository(1, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it, null)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GameCreator>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GameCreator>) {
        singleWrapperGameCreatorRepository(params.key, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it)
        }
    }

    private fun singleWrapperGameCreatorRepository(key: Int, requestedLoadSize: Int, onSuccess: Consumer<PagingResult<GameCreator>>) {
        return gameCreatorRepository.getGameCreatorList(key, requestedLoadSize)
            .wrapSingleWithPreparingComponent(networkServiceMutableLiveData, errorFetchDataResultMutableLiveData)
            .setNetworkStateToLoading()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess)
    }
}