package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.google.gson.annotations.SerializedName

class PagingResult<ListType>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val result: List<ListType>
)