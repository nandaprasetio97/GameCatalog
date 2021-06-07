package com.nandaprasetio.gamecatalog.core.domain.entity.game

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.gamecatalog.core.domain.entity.*
import com.nandaprasetio.gamecatalog.core.domain.entity.genre.GenreShortInfo
import com.nandaprasetio.gamecatalog.core.domain.entity.platform.PlatformGameInfo
import com.nandaprasetio.gamecatalog.core.domain.entity.platform.PlatformParent
import com.nandaprasetio.gamecatalog.core.domain.entity.platform.PlatformShortInfo
import com.nandaprasetio.gamecatalog.core.domain.entity.store.StoreInfo
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.TagShortInfo

open class Game(
    id: Int,
    slug: String?,
    name: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("tba")
    val tba: Boolean,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("rating_top")
    val ratingTop: Int,
    @SerializedName("ratings")
    val ratings: List<Rating>,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("ratings_text_count")
    val ratingsTextCount: Int,
    added: Int,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus,
    @SerializedName("metacritic")
    val metacritic: Int,
    @SerializedName("playtime")
    val playtime: Int,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("user_game")
    val userGame: String?,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("saturated_color")
    val saturatedColor: String?,
    @SerializedName("dominant_color")
    val dominantColor: String?,
    @SerializedName("platforms")
    val platforms: List<PlatformGameInfo>,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<PlatformParent>,
    @SerializedName("genres")
    val genres: List<GenreShortInfo>,
    @SerializedName("stores")
    val stores: List<StoreInfo>,
    @SerializedName("clip")
    val clip: Clip?,
    @SerializedName("tags")
    val tags: List<TagShortInfo>,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<Screenshot>,
): GameShortInfo(id, slug, name, added)