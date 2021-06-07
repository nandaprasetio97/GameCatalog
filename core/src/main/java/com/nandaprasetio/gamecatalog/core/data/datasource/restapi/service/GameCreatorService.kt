package com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service

import com.nandaprasetio.gamecatalog.core.BuildConfig
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreatorDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameCreatorService {
    @GET("creators?key=${BuildConfig.API_KEY}")
    fun getGameCreatorList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResult<GameCreator>>

    @GET("creators/{id}?key=${BuildConfig.API_KEY}")
    fun getGameCreatorDetail(
        @Path("id") id: Int
    ): Single<GameCreatorDetail>
}