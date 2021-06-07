package com.nandaprasetio.gamecatalog.core.domain.entity.store

import com.google.gson.annotations.SerializedName

class StoreDetail(
    id: String?,
    name: String?,
    slug: String?,
    domain: String?,
    gamesCount: String?,
    imageBackground: String?,
    @SerializedName("description")
    val description: String?,
): StoreShortInfo(id, name, slug, domain, gamesCount, imageBackground)