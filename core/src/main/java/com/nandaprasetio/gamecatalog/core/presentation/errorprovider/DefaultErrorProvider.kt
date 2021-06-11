package com.nandaprasetio.gamecatalog.core.presentation.errorprovider

import android.content.Context
import com.nandaprasetio.gamecatalog.core.exception.NotFoundException
import java.io.IOException

open class DefaultErrorProvider: ErrorProvider {
    override fun provideErrorProviderResult(t: Throwable, context: Context): ErrorProviderResult {
        return when (t) {
            is IOException -> ErrorProviderResult("Koneksi Internet Bermasalah", "Terdapat masalah pada koneksi internet. Silahkan mencoba lagi.")
            is NotFoundException -> ErrorProviderResult("Data Tidak Ditemukan", "Untuk saat ini data tidak ditemukan.")
            else -> ErrorProviderResult("Ada Kesalahan", t.message)
        }
    }
}