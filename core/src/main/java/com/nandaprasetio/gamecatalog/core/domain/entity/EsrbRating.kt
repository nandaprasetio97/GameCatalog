package com.nandaprasetio.gamecatalog.core.domain.entity

import com.google.gson.annotations.SerializedName

class EsrbRating(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)