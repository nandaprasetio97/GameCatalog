package com.nandaprasetio.gamecatalog.core.domain.usecase.tagusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.tagpagingdatasource.TagPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.tagpagingdatasource.TagPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.tagrepository.TagRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.Tag
import com.nandaprasetio.gamecatalog.core.ext.subscribeWithComposeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultTagUseCase @Inject constructor(
    private val tagRepository: TagRepository
): TagUseCase {
    override fun getTagDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        tagPagingDataSourceMutableLiveData: MutableLiveData<TagPagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Tag>>>
    ): TagPagingDataSourceFactory {
        return TagPagingDataSourceFactory(
            tagRepository, tagPagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getTagData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<Tag>>,
        onFailed: Consumer<Throwable>
    ) {
        tagRepository.getTagList(page, pageSize)
            .subscribeWithComposeDisposable(compositeDisposable, onSuccess, onFailed)
    }
}