package com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamecreatorpagingdatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository.GameCreatorRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.disposables.CompositeDisposable

class GameCreatorPagingDataSourceFactory(
    private val gameCreatorRepository: GameCreatorRepository,
    private val gameCreatorPagingDataSourceMutableLiveData: MutableLiveData<GameCreatorPagingDataSource>,
    private val networkStateMutableLiveData: MutableLiveData<Int>,
    private val errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<GameCreator>>>,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, GameCreator>() {
    override fun create(): DataSource<Int, GameCreator> {
        return GameCreatorPagingDataSource(
            networkStateMutableLiveData,
            errorFetchDataResultMutableLiveData,
            gameCreatorRepository,
            compositeDisposable
        ).also {
            gameCreatorPagingDataSourceMutableLiveData.postValue(it)
        }
    }
}