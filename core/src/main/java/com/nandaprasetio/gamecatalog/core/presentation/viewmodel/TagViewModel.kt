package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.tagpagingdatasource.TagPagingDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.Tag
import com.nandaprasetio.gamecatalog.core.domain.usecase.tagusecase.TagUseCase
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.TagItemModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val tagUseCase: TagUseCase
): PagingDataViewModel<Int, Tag, TagPagingDataSource>() {
    private val tagPagedListLiveData: LiveData<PagedList<TagItemModelValue>> = LivePagedListBuilder(
        tagUseCase.getTagDataSourceFactory(
            compositeDisposable, getPagingDataSourceLiveData(),
            networkStatusMutableLiveData, errorFetchResultMutableLiveData
        ).map { TagItemModelValue(it) }, pagedListConfig
    ).build()

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>> {
        return tagPagedListLiveData as LiveData<PagedList<BaseModelValue>>
    }
}