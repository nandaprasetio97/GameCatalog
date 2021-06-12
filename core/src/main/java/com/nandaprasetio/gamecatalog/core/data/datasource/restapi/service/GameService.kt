package com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service

import com.nandaprasetio.gamecatalog.core.BuildConfig
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {
    @GET("games?key=${BuildConfig.API_KEY}")
    fun getGameList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): Single<PagingResult<Game>>

    @GET("games?key=${BuildConfig.API_KEY}")
    fun getGameBasedGameDeveloperList(
        @Query("developers") developersSlug: String?,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): Single<PagingResult<Game>>

    @GET("games/{id}?key=${BuildConfig.API_KEY}")
    fun getGameDetail(
        @Path("id") id: Int
    ): Single<GameDetail>
}