package com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service

import com.nandaprasetio.gamecatalog.core.BuildConfig
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameDeveloperService {
    @GET("developers?key=${BuildConfig.API_KEY}")
    fun getGameDeveloperList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResult<GameDeveloper>>

    @GET("developers/{id}?key=${BuildConfig.API_KEY}")
    fun getGameDeveloperDetail(
        @Path("id") id: Int
    ): Single<GameDeveloperDetail>
}