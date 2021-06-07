package com.nandaprasetio.gamecatalog.core.presentation.errorprovider

import android.content.Context
import java.io.IOException

open class DefaultErrorProvider: ErrorProvider {
    override fun provideErrorProviderResult(t: Throwable, context: Context): ErrorProviderResult {
        return when (t) {
            is IOException -> ErrorProviderResult("Koneksi Internet Bermasalah", "Terdapat masalah pada koneksi internet. Silahkan mencoba lagi.")
            else -> ErrorProviderResult("Ada Kesalahan", t.message)
        }
    }
}