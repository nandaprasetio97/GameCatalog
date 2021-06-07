package com.nandaprasetio.gamecatalog.core.domain.entity.store

import com.google.gson.annotations.SerializedName

class StoreInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("store")
    val store: Store
)