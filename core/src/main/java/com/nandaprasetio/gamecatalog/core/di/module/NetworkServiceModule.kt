package com.nandaprasetio.gamecatalog.core.di.module

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class NetworkServiceModule {
    @Provides
    fun provideCreatorRoleService(retrofit: Retrofit): CreatorRoleService {
        return retrofit.create(CreatorRoleService::class.java)
    }

    @Provides
    fun provideGameCreatorService(retrofit: Retrofit): GameCreatorService {
        return retrofit.create(GameCreatorService::class.java)
    }

    @Provides
    fun provideGameService(retrofit: Retrofit): GameService {
        return retrofit.create(GameService::class.java)
    }

    @Provides
    fun provideGameDeveloperService(retrofit: Retrofit): GameDeveloperService {
        return retrofit.create(GameDeveloperService::class.java)
    }

    @Provides
    fun provideGenreService(retrofit: Retrofit): GenreService {
        return retrofit.create(GenreService::class.java)
    }

    @Provides
    fun provideStoreService(retrofit: Retrofit): StoreService {
        return retrofit.create(StoreService::class.java)
    }

    @Provides
    fun provideTagService(retrofit: Retrofit): TagService {
        return retrofit.create(TagService::class.java)
    }
}