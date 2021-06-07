package com.nandaprasetio.gamecatalog.di.module

import com.nandaprasetio.gamecatalog.presentation.DefaultParallelFetchDataResultModelFactory
import com.nandaprasetio.gamecatalog.core.presentation.ParallelFetchDataResultModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppPresentationModule {
    @Provides
    @Singleton
    fun provideDefaultParallelFetchDataResultModelFactory(): ParallelFetchDataResultModelFactory {
        return DefaultParallelFetchDataResultModelFactory()
    }
}