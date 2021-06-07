package com.nandaprasetio.gamecatalog.core.domain.entity

import com.google.gson.annotations.SerializedName

class CreatorRole(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)