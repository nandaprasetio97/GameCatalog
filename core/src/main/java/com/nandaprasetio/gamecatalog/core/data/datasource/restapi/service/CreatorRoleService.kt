package com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service

import com.nandaprasetio.gamecatalog.core.BuildConfig
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CreatorRoleService {
    @GET("creator-roles?key=${BuildConfig.API_KEY}")
    fun getCreatorRoleList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResult<CreatorRole>>
}