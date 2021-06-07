package com.nandaprasetio.gamecatalog.core.domain.entity.genre

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameShortInfo

class Genre(
    id: Int,
    name: String?,
    slug: String?,
    gamesCount: Int,
    imageBackground: String?,
    @SerializedName("games")
    val games: List<GameShortInfo>
): GenreShortInfo(id, name, slug, gamesCount, imageBackground)