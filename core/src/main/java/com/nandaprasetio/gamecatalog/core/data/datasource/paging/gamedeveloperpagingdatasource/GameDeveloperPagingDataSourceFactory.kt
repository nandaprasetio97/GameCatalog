package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamedeveloperpagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.GameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable

class GameDeveloperPagingDataSourceFactory(
    private val gameDeveloperRepository: GameDeveloperRepository,
    private val gameDeveloperPagingDataSourceMutableLiveData: MutableLiveData<GameDeveloperPagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameDeveloper>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, GameDeveloper>() {
    override fun create(): DataSource<Int, GameDeveloper> {
        return GameDeveloperPagingDataSource(
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            gameDeveloperRepository,
            compositeDisposable
        ).also {
            gameDeveloperPagingDataSourceMutableLiveData.postValue(it)
        }
    }
}