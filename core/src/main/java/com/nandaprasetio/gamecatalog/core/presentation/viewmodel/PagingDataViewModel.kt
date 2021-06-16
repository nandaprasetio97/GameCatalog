package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue

abstract class PagingDataViewModel<Key, Value, DS: DataSource<Key, Value>>: ParallelLoadingDataViewModel() {
    // Network Status Live Data
    protected val networkStatusMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val networkStatusLiveData: LiveData<Int> = networkStatusMutableLiveData

    // Error Fetch Result Live Data
    protected val errorFetchResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Value>>> = MutableLiveData()
    val errorFetchDataResultLiveData: LiveData<FetchDataResult.Error<PagingResult<Value>>> = errorFetchResultMutableLiveData

    // Paged List Config
    protected val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(20)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    private val basePagingDataSourceLiveData: MutableLiveData<DS> = MutableLiveData()

    init {
        parallelFetchDataResultMapMutableLiveData.value = parallelFetchDataResultMap
    }

    abstract fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>>

    protected fun getPagingDataSourceLiveData(): MutableLiveData<DS> {
        return basePagingDataSourceLiveData
    }

    override fun onRefreshInParallelLoadingData() {
        basePagingDataSourceLiveData.value?.invalidate()
        onRefreshInPagingData()
    }

    protected open fun onRefreshInPagingData() {}

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}