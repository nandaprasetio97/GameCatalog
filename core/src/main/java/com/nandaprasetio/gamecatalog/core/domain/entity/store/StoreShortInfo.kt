package com.nandaprasetio.gamecatalog.core.domain.entity.store

import com.google.gson.annotations.SerializedName

open class StoreShortInfo(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("domain")
    val domain: String?,
    @SerializedName("games_count")
    val gamesCount: String?,
    @SerializedName("image_background")
    val imageBackground: String?
)