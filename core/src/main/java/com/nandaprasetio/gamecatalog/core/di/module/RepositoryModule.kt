package com.nandaprasetio.gamecatalog.core.di.module

import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.CreatorRoleRepository
import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.DefaultCreatorRoleRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository.DefaultGameCreatorRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository.GameCreatorRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.DefaultGameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.GameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.DefaultGameRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.DefaultGenreRepository
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.GenreRepository
import com.nandaprasetio.gamecatalog.core.data.repository.storerepository.DefaultStoreRepository
import com.nandaprasetio.gamecatalog.core.data.repository.storerepository.StoreRepository
import com.nandaprasetio.gamecatalog.core.data.repository.tagrepository.DefaultTagRepository
import com.nandaprasetio.gamecatalog.core.data.repository.tagrepository.TagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideDefaultCreatorRoleRepository(defaultCreatorRoleRepository: DefaultCreatorRoleRepository): CreatorRoleRepository

    @Binds
    @Singleton
    abstract fun provideDefaultGameCreatorRepository(defaultGameCreatorRepository: DefaultGameCreatorRepository): GameCreatorRepository

    @Binds
    @Singleton
    abstract fun provideDefaultGameDeveloperRepository(defaultGameDeveloperRepository: DefaultGameDeveloperRepository): GameDeveloperRepository

    @Binds
    @Singleton
    abstract fun provideDefaultGameRepository(defaultGameRepository: DefaultGameRepository): GameRepository

    @Binds
    @Singleton
    abstract fun provideDefaultGenreRepository(defaultGenreRepository: DefaultGenreRepository): GenreRepository

    @Binds
    @Singleton
    abstract fun provideDefaultStoreRepository(defaultStoreRepository: DefaultStoreRepository): StoreRepository

    @Binds
    @Singleton
    abstract fun provideDefaultTagRepository(defaultTagRepository: DefaultTagRepository): TagRepository
}