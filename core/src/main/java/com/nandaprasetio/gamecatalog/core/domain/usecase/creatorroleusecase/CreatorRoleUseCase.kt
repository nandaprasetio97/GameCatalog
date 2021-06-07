package com.nandaprasetio.gamecatalog.core.domain.usecase.creatorroleusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.creatorrolepagingdatasource.CreatorRolePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.creatorrolepagingdatasource.CreatorRolePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamecreatorpagingdatasource.GameCreatorPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamepagingdatasource.GamePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface CreatorRoleUseCase {
    fun getCreatorRoleDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        creatorRolePagingDataSourceMutableLiveData: MutableLiveData<CreatorRolePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<CreatorRole>>>
    ): CreatorRolePagingDataSourceFactory

    fun getGameCreatorData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<CreatorRole>>,
        onFailed: Consumer<Throwable>
    )
}