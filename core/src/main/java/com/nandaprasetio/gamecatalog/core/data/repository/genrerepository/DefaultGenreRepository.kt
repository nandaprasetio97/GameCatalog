package com.nandaprasetio.gamecatalog.core.data.repository.genrerepository

import com.nandaprasetio.gamecatalog.core.data.datasource.content.genredatasource.GenreDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGenreRepository @Inject constructor(
    private val genreDataSource: GenreDataSource
): GenreRepository {
    override fun getGenreList(page: Int, pageSize: Int): Single<PagingResult<Genre>> {
        return genreDataSource.getGenreList(page, pageSize)
    }

    override fun getGenreDetail(id: Int): Single<GenreDetail> {
        return genreDataSource.getGenreDetail(id)
    }
}