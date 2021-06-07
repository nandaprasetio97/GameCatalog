package com.nandaprasetio.gamecatalog.core.domain.entity.game

import com.google.gson.annotations.SerializedName

open class GameShortInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("added")
    val added: Int
)