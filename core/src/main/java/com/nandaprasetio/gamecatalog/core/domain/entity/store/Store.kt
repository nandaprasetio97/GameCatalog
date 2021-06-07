package com.nandaprasetio.gamecatalog.core.domain.entity.store

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameShortInfo

class Store(
    id: String?,
    name: String?,
    slug: String?,
    domain: String?,
    gamesCount: String?,
    imageBackground: String?,
    @SerializedName("games")
    val games: List<GameShortInfo>,
): StoreShortInfo(id, name, slug, domain, gamesCount, imageBackground)