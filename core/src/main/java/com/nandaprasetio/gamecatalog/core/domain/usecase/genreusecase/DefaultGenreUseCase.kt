package com.nandaprasetio.gamecatalog.core.domain.usecase.genreusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource.GenrePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.genrepagingdatasource.GenrePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.GenreRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.subscribeWithComposeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultGenreUseCase @Inject constructor(
    private val genreRepository: GenreRepository
): GenreUseCase {
    override fun getGenreDataSourceFactory(
        compositeDisposable: CompositeDisposable,
        genrePagingDataSourceMutableLiveData: MutableLiveData<GenrePagingDataSource>,
        networkStatusMutableLiveData: MutableLiveData<Int>,
        errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Genre>>>
    ): GenrePagingDataSourceFactory {
        return GenrePagingDataSourceFactory(
            genreRepository, genrePagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getGenreData(
        page: Int, pageSize: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<PagingResult<Genre>>,
        onFailed: Consumer<Throwable>
    ) {
        genreRepository.getGenreList(page, pageSize)
            .subscribeWithComposeDisposable(compositeDisposable, onSuccess, onFailed)
    }
}