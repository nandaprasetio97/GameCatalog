package com.nandaprasetio.gamecatalog.core.domain.entity

import com.google.gson.annotations.SerializedName

class Rating(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("percent")
    val percent: Float
)