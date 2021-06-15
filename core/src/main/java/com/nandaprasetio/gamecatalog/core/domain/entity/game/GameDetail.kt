package com.nandaprasetio.gamecatalog.core.domain.entity.game

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.gamecatalog.core.domain.entity.*
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreShortInfo
import com.nandaprasetio.gamecatalog.core.domain.entity.platform.PlatformGameInfo
import com.nandaprasetio.gamecatalog.core.domain.entity.platform.PlatformParent
import com.nandaprasetio.gamecatalog.core.domain.entity.store.StoreInfo
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.TagShortInfo

class GameDetail(
    id: Int,
    slug: String?,
    name: String?,
    @SerializedName("description")
    val description: String?,
    released: String?,
    tba: Boolean,
    backgroundImage: String?,
    rating: Float,
    ratingTop: Int,
    ratings: List<Rating>,
    ratingsCount: Int,
    ratingsTextCount: Int,
    added: Int,
    addedByStatus: AddedByStatus,
    metacritic: Int,
    playtime: Int,
    suggestionsCount: Int,
    updated: String?,
    userGame: String?,
    reviewsCount: Int,
    saturatedColor: String?,
    dominantColor: String?,
    platforms: List<PlatformGameInfo>,
    parentPlatforms: List<PlatformParent>,
    genres: List<GenreShortInfo>,
    stores: List<StoreInfo>,
    clip: Clip?,
    tags: List<TagShortInfo>,
    shortScreenshots: List<Screenshot>
): Game(id, slug, name, released, tba, backgroundImage, rating, ratingTop, ratings, ratingsCount,
    ratingsTextCount, added, addedByStatus, metacritic, playtime, suggestionsCount, updated,
    userGame, reviewsCount, saturatedColor, dominantColor, platforms, parentPlatforms, genres,
    stores, clip, tags, shortScreenshots
)