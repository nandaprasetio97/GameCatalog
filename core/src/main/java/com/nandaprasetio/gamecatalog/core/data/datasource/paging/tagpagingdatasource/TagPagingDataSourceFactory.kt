package com.nandaprasetio.gamecatalog.core.data.datasource.paging.tagpagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.repository.tagrepository.TagRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.Tag
import io.reactivex.disposables.CompositeDisposable

class TagPagingDataSourceFactory(
    private val tagRepository: TagRepository,
    private val tagPagingDataSourceMutableLiveData: MutableLiveData<TagPagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Tag>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, Tag>() {
    override fun create(): DataSource<Int, Tag> {
        return TagPagingDataSource(
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            tagRepository,
            compositeDisposable
        ).also {
            tagPagingDataSourceMutableLiveData.postValue(it)
        }
    }
}