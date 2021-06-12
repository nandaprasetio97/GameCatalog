package com.nandaprasetio.gamecatalog.core.domain.usecase.genredetailusecase

import androidx.lifecycle.MutableLiveData
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgamedeveloperpagingdatasource.GameBasedGameDeveloperPagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgenrepagingdatasource.GameBasedGenrePagingDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.paging.gamebasedgenrepagingdatasource.GameBasedGenrePagingDataSourceFactory
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.GenreRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.ext.wrapSingle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class DefaultGenreDetailUseCase @Inject constructor(
    private val genreRepository: GenreRepository,
    private val gameRepository: GameRepository
): GenreDetailUseCase {
    override fun getGameBasedGenreDataSourceFactory(slug: String?, compositeDisposable: CompositeDisposable, gameBasedGenrePagingDataSourceMutableLiveData: MutableLiveData<GameBasedGenrePagingDataSource>, networkStatusMutableLiveData: MutableLiveData<Int>, errorFetchDataResultMutableLiveData: MutableLiveData<FetchDataResult.Error<PagingResult<Game>>>): GameBasedGenrePagingDataSourceFactory {
        return GameBasedGenrePagingDataSourceFactory(
            slug, gameRepository, gameBasedGenrePagingDataSourceMutableLiveData,
            networkStatusMutableLiveData, errorFetchDataResultMutableLiveData, compositeDisposable
        )
    }

    override fun getGenreDetail(id: Int, compositeDisposable: CompositeDisposable, onSuccess: Consumer<GenreDetail>, onFailed: Consumer<Throwable>) {
        return genreRepository.getGenreDetail(id)
            .wrapSingle()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess, onFailed)
    }
}