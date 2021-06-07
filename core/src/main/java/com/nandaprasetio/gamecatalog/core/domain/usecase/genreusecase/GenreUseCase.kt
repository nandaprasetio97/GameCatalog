package com.nandaprasetio.gamecatalog.core.domain.usecase.genreusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource.GenrePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource.GenrePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface GenreUseCase {
    fun getGenreDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        genrePagingDataSourceMutableLiveData: MutableLiveData<GenrePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Genre>>>
    ): GenrePagingDataSourceFactory

    fun getGenreData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<Genre>>,
        onFailed: Consumer<Throwable>
    )
}