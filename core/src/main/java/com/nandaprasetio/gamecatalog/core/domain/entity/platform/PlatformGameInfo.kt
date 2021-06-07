package com.nandaprasetio.gamecatalog.core.domain.entity.platform

import com.google.gson.annotations.SerializedName
import com.nandaprasetio.gamecatalog.core.domain.entity.Requirement

class PlatformGameInfo(
    @SerializedName("platform")
    val platform: Platform,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements_en")
    val requirementsEn: Requirement?,
    @SerializedName("requirements_ru")
    val requirementsRu: Requirement?,
)