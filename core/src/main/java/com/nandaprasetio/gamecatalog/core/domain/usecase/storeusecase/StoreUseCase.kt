package com.nandaprasetio.gamecatalog.core.domain.usecase.storeusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.storepagingdatasource.StorePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.storepagingdatasource.StorePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface StoreUseCase {
    fun getStoreDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        storePagingDataSourceMutableLiveData: MutableLiveData<StorePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Store>>>
    ): StorePagingDataSourceFactory

    fun getStoreData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<Store>>,
        onFailed: Consumer<Throwable>
    )
}