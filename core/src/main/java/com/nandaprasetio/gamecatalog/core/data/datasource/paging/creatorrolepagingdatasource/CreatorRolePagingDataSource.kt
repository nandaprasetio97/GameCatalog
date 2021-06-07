package com.nandaprasetio.gamecatalog.core.data.datasource.paging.creatorrolepagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.CreatorRoleRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.onResultWithPagingResultAsValueAndIntAsKey
import com.nandaprasetio.gamecatalog.core.ext.wrapSingleWithPreparingComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class CreatorRolePagingDataSource(
    private val networkServiceMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<CreatorRole>>>,
    private val creatorRoleRepository: CreatorRoleRepository,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, CreatorRole>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CreatorRole>) {
        singleWrapperGameCreatorRepository(1, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it, null)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CreatorRole>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CreatorRole>) {
        singleWrapperGameCreatorRepository(params.key, params.requestedLoadSize) {
            callback.onResultWithPagingResultAsValueAndIntAsKey(it)
        }
    }

    private fun singleWrapperGameCreatorRepository(key: Int, requestedLoadSize: Int, onSuccess: Consumer<PagingResult<CreatorRole>>) {
        return creatorRoleRepository.getCreatorRoleList(key, requestedLoadSize)
            .wrapSingleWithPreparingComponent(networkServiceMutableLiveData, errorFetchDataResultMutableLiveData)
            .setNetworkStateToLoading()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess)
    }
}