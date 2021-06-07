package com.nandaprasetio.gamecatalog.core.presentation.errorprovider

import androidx.annotation.DrawableRes

class ErrorProviderResult(
    val error: String?,
    val errorDescription: String?,
    @DrawableRes
    val errorDrawableRes: Int? = null,
)