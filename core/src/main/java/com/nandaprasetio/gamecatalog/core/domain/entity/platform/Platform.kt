package com.nandaprasetio.gamecatalog.core.domain.entity.platform

import com.google.gson.annotations.SerializedName

class Platform(
    id: Int,
    name: String?,
    slug: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("year_end")
    val yearEnd: Int?,
    @SerializedName("year_start")
    val yearStart: Int?,
    @SerializedName("games_count")
    val gamesCount: Int?,
    @SerializedName("image_background")
    val imageBackground: String?,
): PlatformShortInfo(id, name, slug)