package com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service

import com.nandaprasetio.gamecatalog.core.BuildConfig
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenreService {
    @GET("genres?key=${BuildConfig.API_KEY}")
    fun getGenreList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResult<Genre>>

    @GET("genres/{id}?key=${BuildConfig.API_KEY}")
    fun getGenreDetail(
        @Path("id") id: Int
    ): Single<GenreDetail>
}