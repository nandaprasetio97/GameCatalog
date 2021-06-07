package com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service

import com.nandaprasetio.gamecatalog.core.BuildConfig
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import com.nandaprasetio.gamecatalog.core.domain.entity.store.StoreDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreService {
    @GET("stores?key=${BuildConfig.API_KEY}")
    fun getStoreList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResult<Store>>

    @GET("stores/{id}?key=${BuildConfig.API_KEY}")
    fun getStoreDetail(
        @Path("id") id: Int
    ): Single<StoreDetail>
}