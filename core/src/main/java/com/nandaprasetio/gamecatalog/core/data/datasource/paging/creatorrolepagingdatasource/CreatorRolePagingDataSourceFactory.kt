package com.nandaprasetio.gamecatalog.core.data.datasource.paging.creatorrolepagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.CreatorRoleRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable

class CreatorRolePagingDataSourceFactory(
    private val creatorRoleRepository: CreatorRoleRepository,
    private val creatorRolePagingDataSourceMutableLiveData: MutableLiveData<CreatorRolePagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<CreatorRole>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, CreatorRole>() {
    override fun create(): DataSource<Int, CreatorRole> {
        return CreatorRolePagingDataSource(
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            creatorRoleRepository,
            compositeDisposable
        ).also {
            creatorRolePagingDataSourceMutableLiveData.postValue(it)
        }
    }
}