package com.nandaprasetio.gamecatalog.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource.GameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperusecase.GameDeveloperUseCase
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.GameDeveloperItemModelValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDeveloperViewModel @Inject constructor(
    private val gameDeveloperUseCase: GameDeveloperUseCase
): PagingDataViewModel<Int, GameDeveloper, GameDeveloperPagingDataSource>() {
    private val gameDeveloperPagedListLiveData: LiveData<PagedList<GameDeveloperItemModelValue>> = LivePagedListBuilder(
        gameDeveloperUseCase.getGameDeveloperDataSourceFactory(
            compositeDisposable, getPagingDataSourceLiveData(),
            networkStatusMutableLiveData, errorFetchResultMutableLiveData
        ).map { GameDeveloperItemModelValue(it) }, pagedListConfig
    ).build()

    @Suppress("UNCHECKED_CAST")
    override fun getPagedListLiveData(): LiveData<PagedList<BaseModelValue>> {
        return gameDeveloperPagedListLiveData as LiveData<PagedList<BaseModelValue>>
    }
}