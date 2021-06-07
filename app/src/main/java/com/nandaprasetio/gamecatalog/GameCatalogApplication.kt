package com.nandaprasetio.gamecatalog

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GameCatalogApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}