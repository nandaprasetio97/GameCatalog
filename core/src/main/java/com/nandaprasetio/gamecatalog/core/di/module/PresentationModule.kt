package com.nandaprasetio.gamecatalog.core.di.module

import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.DefaultErrorProvider
import com.nandaprasetio.gamecatalog.core.presentation.errorprovider.ErrorProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {
    @Provides
    @Singleton
    fun provideErrorProvider(): ErrorProvider {
        return DefaultErrorProvider()
    }
}