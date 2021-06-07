package com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameShortInfo

class GameDeveloper(
    id: Int,
    name: String?,
    slug: String?,
    gamesCount: Int,
    imageBackground: String?,
    @SerializedName("games")
    val games: List<GameShortInfo>
): GameDeveloperShortInfo(id, name, slug, gamesCount, imageBackground)