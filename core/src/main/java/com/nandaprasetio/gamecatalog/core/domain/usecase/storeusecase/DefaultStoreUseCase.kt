package com.nandaprasetio.gamecatalog.core.domain.usecase.storeusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.storepagingdatasource.StorePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.storepagingdatasource.StorePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.storerepository.StoreRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import com.nandaprasetio.gamecatalog.core.ext.subscribeWithComposeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository
): StoreUseCase {
    override fun getStoreDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        storePagingDataSourceMutableLiveData: MutableLiveData<StorePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Store>>>
    ): StorePagingDataSourceFactory {
        return StorePagingDataSourceFactory(
            storeRepository, storePagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getStoreData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<Store>>, onFailed: Consumer<Throwable>
    ) {
        storeRepository.getStoreList(page, pageSize)
            .subscribeWithComposeDisposable(compositeDisposable, onSuccess, onFailed)
    }
}