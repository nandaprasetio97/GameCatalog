package com.nandaprasetio.gamecatalog.core.ext

import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalStateException

fun<T> Single<T>.wrapSingle(): SingleWrapper<T> {
    return SingleWrapper(this)
}

fun<T> Single<T>.wrapSingleWithPreparingComponent(networkServiceMutableLiveData: MutableLiveData<Int>, errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<T>>): SingleWrapper<T> {
    return SingleWrapper(this)
        .setBothRequiredWithThis(networkServiceMutableLiveData, errorFetchDataResultMutableLiveData)
}

private fun<T> Single<T>.subscribeWithComposeDisposableAndCallback(
    compositeDisposable: CompositeDisposable,
    onSuccess: Consumer<T>?,
    onError: Consumer<Throwable>? = null,
    changeNetworkServiceMutableLiveDataState: ((Int) -> Unit)? = null,
    changeErrorFetchDataResultMutableLiveDataState: ((FetchDataResult.Error<T>?) -> Unit)? = null
) {
    this.subscribeWithComposeDisposable(
        compositeDisposable = compositeDisposable,
        onSuccess = {
            changeNetworkServiceMutableLiveDataState?.invoke(0)
            onSuccess?.accept(it)
        },
        onError = {
            changeErrorFetchDataResultMutableLiveDataState?.invoke(FetchDataResult.Error(it))
            changeNetworkServiceMutableLiveDataState?.invoke(-2)
            onError?.accept(it)
        }
    )
}

fun<T> Single<T>.subscribeWithComposeDisposable(
    compositeDisposable: CompositeDisposable,
    onSuccess: Consumer<T>?,
    onError: Consumer<Throwable>? = null
) {
    compositeDisposable.add(
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
    )
}

class SingleWrapper<T>(
    private val single: Single<T>
) {
    private var _networkServiceMutableLiveData: MutableLiveData<Int>? = null
    private var _errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<T>>? = null

    fun setNetworkServiceMutableLiveData(networkServiceMutableLiveData: MutableLiveData<Int>): SingleWrapper<T> {
        _networkServiceMutableLiveData = networkServiceMutableLiveData
        return this
    }

    fun setErrorFetchDataResultMutableLiveData(errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<T>>): SingleWrapper<T> {
        _errorFetchDataResultMutableLiveData = errorFetchDataResultMutableLiveData
        return this
    }

    fun setNetworkStateToLoading(): SingleWrapper<T> {
        if (_networkServiceMutableLiveData == null) {
            throw IllegalStateException("Network service mutable live data must be set through setNetworkServiceMutableLiveData() method.")
        }
        _networkServiceMutableLiveData?.postValue(-1)
        return this
    }

    fun setBothRequiredWithThis(networkServiceMutableLiveData: MutableLiveData<Int>, errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<T>>): SingleWrapper<T> {
        setNetworkServiceMutableLiveData(networkServiceMutableLiveData)
        setErrorFetchDataResultMutableLiveData(errorFetchDataResultMutableLiveData)
        return this
    }

    fun subscribeWithComposeDisposableAndCallback(
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<T>?,
        onError: Consumer<Throwable>? = null
    ) {
        single.subscribeWithComposeDisposableAndCallback(
            compositeDisposable, onSuccess, onError,
            { _networkServiceMutableLiveData?.postValue(it) },
            { _errorFetchDataResultMutableLiveData?.postValue(it) }
        )
    }
}

fun<Value> PageKeyedDataSource.LoadInitialCallback<Int, Value>.onResultWithPagingResultAsValueAndIntAsKey(
    pagingResult: PagingResult<Value>, previousPageKey: Int?
) {
    this.onResult(pagingResult.result, previousPageKey, getPageNumberFromNextPageUrl(pagingResult.next))
}

fun<Value> PageKeyedDataSource.LoadCallback<Int, Value>.onResultWithPagingResultAsValueAndIntAsKey(
    pagingResult: PagingResult<Value>
) {
    this.onResult(pagingResult.result, getPageNumberFromNextPageUrl(pagingResult.next))
}

private fun getPageNumberFromNextPageUrl(next: String?): Int? {
    return next?.toUri()?.getQueryParameter("page")?.toInt()
}