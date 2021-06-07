package com.nandaprasetio.gamecatalog.core.domain.entity.platform

import com.google.gson.annotations.SerializedName

open class PlatformShortInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
)