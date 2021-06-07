package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.google.gson.annotations.SerializedName

abstract class BasePagingResult<ListType>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("result")
    val result: List<ListType>
)