package com.nandaprasetio.gamecatalog.core.data.repository.genrerepository

import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single

interface GenreRepository {
    fun getGenreList(page: Int, pageSize: Int): Single<PagingResult<Genre>>
    fun getGenreDetail(id: Int): Single<GenreDetail>
}