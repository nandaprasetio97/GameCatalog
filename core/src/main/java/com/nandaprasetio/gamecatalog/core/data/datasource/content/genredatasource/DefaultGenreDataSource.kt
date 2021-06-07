package com.nandaprasetio.gamecatalog.core.data.datasource.content.genredatasource

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.GenreService
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGenreDataSource @Inject constructor(
    private val genreService: GenreService
): GenreDataSource {
    override fun getGenreList(page: Int, pageSize: Int): Single<PagingResult<Genre>> {
        return genreService.getGenreList(page, pageSize)
    }

    override fun getGenreDetail(id: Int): Single<GenreDetail> {
        return genreService.getGenreDetail(id)
    }
}