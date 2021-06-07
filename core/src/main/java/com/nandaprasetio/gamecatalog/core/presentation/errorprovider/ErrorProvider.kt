package com.nandaprasetio.gamecatalog.core.presentation.errorprovider

import android.content.Context

interface ErrorProvider {
    fun provideErrorProviderResult(t: Throwable, context: Context): ErrorProviderResult
}