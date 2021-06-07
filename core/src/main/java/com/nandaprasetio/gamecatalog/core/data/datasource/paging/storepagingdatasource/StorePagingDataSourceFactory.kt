package com.nandaprasetio.gamecatalog.core.data.datasource.paging.storepagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource.GenrePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.storerepository.StoreRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import io.reactivex.disposables.CompositeDisposable

class StorePagingDataSourceFactory(
    private val storeRepository: StoreRepository,
    private val storePagingDataSourceMutableLiveData: MutableLiveData<StorePagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Store>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, Store>() {
    override fun create(): DataSource<Int, Store> {
        return StorePagingDataSource(
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            storeRepository,
            compositeDisposable
        ).also {
            storePagingDataSourceMutableLiveData.postValue(it)
        }
    }
}