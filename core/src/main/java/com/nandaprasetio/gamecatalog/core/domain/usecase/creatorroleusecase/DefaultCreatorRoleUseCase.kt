package com.nandaprasetio.gamecatalog.core.domain.usecase.creatorroleusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.creatorrolepagingdatasource.CreatorRolePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.creatorrolepagingdatasource.CreatorRolePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.CreatorRoleRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.subscribeWithComposeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultCreatorRoleUseCase @Inject constructor(
    private val creatorRoleRepository: CreatorRoleRepository
): CreatorRoleUseCase {
    override fun getCreatorRoleDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        creatorRolePagingDataSourceMutableLiveData: MutableLiveData<CreatorRolePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<CreatorRole>>>
    ): CreatorRolePagingDataSourceFactory {
        return CreatorRolePagingDataSourceFactory(
            creatorRoleRepository, creatorRolePagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getGameCreatorData(
        page: Int,
        pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<CreatorRole>>,
        onFailed: Consumer<Throwable>
    ) {
        creatorRoleRepository.getCreatorRoleList(page, pageSize)
            .subscribeWithComposeDisposable(compositeDisposable, onSuccess, onFailed)
    }
}