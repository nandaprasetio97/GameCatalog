package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.presentation.ParallelMutableLiveData
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.CombinationWithItemModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.carouselmodelvalue.CarouselModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.BaseItemModelValue
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

abstract class ParallelLoadingDataViewModel: ViewModel() {
    protected val parallelFetchDataResultMap: MutableMap<String, BaseModelValue> = mutableMapOf()
    protected val parallelFetchDataResultMapMutableLiveData: MutableLiveData<Map<String, BaseModelValue>> = MutableLiveData()
    val parallelFetchDataResultMapLiveData: LiveData<Map<String, BaseModelValue>> = parallelFetchDataResultMapMutableLiveData
    private val parallelFetchDataResultOnFinishedMutableLiveData: MutableLiveData<BaseModelValue> = ParallelMutableLiveData()
    val parallelFetchDataResultOnFinishedLiveData: LiveData<BaseModelValue> = parallelFetchDataResultOnFinishedMutableLiveData

    // Conposite Disposable
    protected val compositeDisposable = CompositeDisposable()

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
        parallelFetchDataResultOnFinishedMutableLiveData.value = combinationWithItemModelValue
        onFetchData({
            combinationWithItemModelValue.fetchDataResult = FetchDataResult.Success(onMappingToItemModelValue(it))
            parallelFetchDataResultOnFinishedMutableLiveData.postValue(combinationWithItemModelValue)
        }, {
            combinationWithItemModelValue.fetchDataResult = FetchDataResult.Error(it)
            parallelFetchDataResultOnFinishedMutableLiveData.postValue(combinationWithItemModelValue)
        })
    }

    fun refresh() {
        compositeDisposable.clear()
        parallelFetchDataResultMap.clear()
        onRefreshInParallelLoadingData()
    }

    protected open fun onRefreshInParallelLoadingData() {}
}