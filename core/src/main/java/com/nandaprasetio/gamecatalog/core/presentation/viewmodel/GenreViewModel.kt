package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource.GenrePagingDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.usecase.genreusecase.GenreUseCase
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GenreItemModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreUseCase: GenreUseCase
): PagingDataViewModel<Int, Genre, GenrePagingDataSource>() {
    private val genrePagedListLiveData: LiveData<PagedList<GenreItemModelValue>> = LivePagedListBuilder(
        genreUseCase.getGenreDataSourceFactory(
            compositeDisposable, getPagingDataSourceLiveData(),
            networkStatusMutableLiveData, errorFetchResultMutableLiveData
        ).map { GenreItemModelValue(it) }, pagedListConfig
    ).build()

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>> {
        return genrePagedListLiveData as LiveData<PagedList<BaseModelValue>>
    }
}