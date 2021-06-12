package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.presentation.ParallelMutableLiveData
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.CombinationWithItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.carouselmodelvalue.CarouselModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.BaseItemModelValue
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

abstract class PagingDataViewModel<Key, Value, DS: DataSource<Key, Value>>: ViewModel() {
    // Network Status Live Data
    protected val networkStatusMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val networkStatusLiveData: LiveData<Int> = networkStatusMutableLiveData

    // Error Fetch Result Live Data
    protected val errorFetchResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Value>>> = MutableLiveData()
    val errorFetchDataResultLiveData: LiveData<FetchDataResult.Error<PagingResult<Value>>> = errorFetchResultMutableLiveData

    // Conposite Disposable
    protected val compositeDisposable = CompositeDisposable()

    // Paged List Config
    protected val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(20)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    // Parallel Fetch Data Result Map
    private val parallelFetchDataResultMap: MutableMap<String, BaseModelValue> = mutableMapOf()
    private val parallelFetchDataResultMapMutableLiveData: MutableLiveData<Map<String, BaseModelValue>> = MutableLiveData()
    val parallelFetchDataResultMapLiveData: LiveData<Map<String, BaseModelValue>> = parallelFetchDataResultMapMutableLiveData
    private val parallelFetchDataResultOnFinishedMutableLiveData: MutableLiveData<BaseModelValue> = ParallelMutableLiveData()
    val parallelFetchDataResultOnFinishedLiveData: LiveData<BaseModelValue> = parallelFetchDataResultOnFinishedMutableLiveData

    private val basePagingDataSourceLiveData: MutableLiveData<DS> = MutableLiveData()

    init {
        parallelFetchDataResultMapMutableLiveData.value = parallelFetchDataResultMap
    }

    protected fun<I, O: BaseItemModelValue> parallelingFetchDataToCarousel(
        key: String, title: String?, description: String?,
        onFetchData: (Consumer<I>, Consumer<Throwable>) -> Unit,
        onMappingToItemModelValue: (I) -> List<O>
    ) {
        val carouselModelValue: CarouselModelValue<O> = CarouselModelValue(
            title, description, null
        )
        parallelFetchDataResultMap[key] = carouselModelValue
        onFetchData({
            carouselModelValue.fetchDataResult = FetchDataResult.Success(onMappingToItemModelValue(it))
            parallelFetchDataResultOnFinishedMutableLiveData.postValue(carouselModelValue)
        }, {
            carouselModelValue.fetchDataResult = FetchDataResult.Error(it)
            parallelFetchDataResultOnFinishedMutableLiveData.postValue(carouselModelValue)
        })
    }

    protected fun<I, O: BaseItemModelValue> parallelingFetchDataToItem(
        key: String,
        onFetchData: (Consumer<I>, Consumer<Throwable>) -> Unit,
        onMappingToItemModelValue: (I) -> O
    ) {
        val combinationWithItemModelValue: CombinationWithItemModelValue<O> = CombinationWithItemModelValue(null)
        parallelFetchDataResultMap[key] = combinationWithItemModelValue
        onFetchData({
            combinationWithItemModelValue.fetchDataResult = FetchDataResult.Success(onMappingToItemModelValue(it))
            parallelFetchDataResultOnFinishedMutableLiveData.postValue(combinationWithItemModelValue)
        }, {
            combinationWithItemModelValue.fetchDataResult = FetchDataResult.Error(it)
            parallelFetchDataResultOnFinishedMutableLiveData.postValue(combinationWithItemModelValue)
        })
    }

    abstract fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>>

    protected fun getPagingDataSourceLiveData(): MutableLiveData<DS> {
        return basePagingDataSourceLiveData
    }

    fun refresh() {
        compositeDisposable.clear()
        basePagingDataSourceLiveData.value?.invalidate()
        onRefresh()
    }

    open fun onRefresh() {}

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}