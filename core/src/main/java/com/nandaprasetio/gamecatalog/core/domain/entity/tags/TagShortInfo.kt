package com.nandaprasetio.gamecatalog.core.domain.entity.tags

import com.google.gson.annotations.SerializedName

open class TagShortInfo(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("image_background")
    val imageBackground: String?
)