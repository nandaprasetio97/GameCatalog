package com.nandaprasetio.gamecatalog.core.domain.entity

import com.google.gson.annotations.SerializedName

class Screenshot(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?
)