package com.nandaprasetio.gamecatalog.core.domain.entity.genre

import com.google.gson.annotations.SerializedName

class GenreDetail(
    id: Int,
    name: String?,
    slug: String?,
    gamesCount: Int,
    imageBackground: String?,
    @SerializedName("description")
    val description: String?
): GenreShortInfo(id, name, slug, gamesCount, imageBackground)