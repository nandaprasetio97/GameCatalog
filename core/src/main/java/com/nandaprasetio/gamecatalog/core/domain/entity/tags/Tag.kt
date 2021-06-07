package com.nandaprasetio.gamecatalog.core.domain.entity.tags

import com.google.gson.annotations.SerializedName

class Tag(
    id: String?,
    name: String?,
    slug: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("game_count")
    val gameCount: Int,
    imageBackground: String?
): TagShortInfo(id, name, slug, imageBackground)