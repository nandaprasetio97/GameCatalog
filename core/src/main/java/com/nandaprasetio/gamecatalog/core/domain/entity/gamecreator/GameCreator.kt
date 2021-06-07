package com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameShortInfo

open class GameCreator(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("image_background")
    val imageBackground: String?,
    @SerializedName("games_count")
    val gamesCount: String?,
    @SerializedName("positions")
    val creatorRole: List<CreatorRole>,
    @SerializedName("games")
    val games: List<GameShortInfo>
)