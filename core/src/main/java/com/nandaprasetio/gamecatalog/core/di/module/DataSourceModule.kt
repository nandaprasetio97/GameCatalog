package com.nandaprasetio.gamecatalog.core.di.module

import com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource.CreatorRoleDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource.DefaultCreatorRoleDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamecreatordatasource.DefaultGameCreatorDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamecreatordatasource.GameCreatorDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedatasource.DefaultGameDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedatasource.GameDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedeveloperdatasource.DefaultGameDeveloperDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedeveloperdatasource.GameDeveloperDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.genredatasource.DefaultGenreDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.genredatasource.GenreDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.storedatasource.DefaultStoreDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.storedatasource.StoreDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.tagdatasource.DefaultTagDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.tagdatasource.TagDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun provideDefaultCreatorRoleDataSource(defaultCreatorRoleDataSource: DefaultCreatorRoleDataSource): CreatorRoleDataSource

    @Binds
    @Singleton
    abstract fun provideDefaultGameCreatorDataSource(defaultGameCreatorDataSource: DefaultGameCreatorDataSource): GameCreatorDataSource

    @Binds
    @Singleton
    abstract fun provideDefaultGameDeveloperDataSource(defaultGameDeveloperDataSource: DefaultGameDeveloperDataSource): GameDeveloperDataSource

    @Binds
    @Singleton
    abstract fun provideDefaultGameDataSource(defaultGameDataSource: DefaultGameDataSource): GameDataSource

    @Binds
    @Singleton
    abstract fun provideDefaultGenreDataSource(defaultGenreDataSource: DefaultGenreDataSource): GenreDataSource

    @Binds
    @Singleton
    abstract fun provideDefaultStoreDataSource(defaultStoreDataSource: DefaultStoreDataSource): StoreDataSource

    @Binds
    @Singleton
    abstract fun provideDefaultTagDataSource(defaultTagDataSource: DefaultTagDataSource): TagDataSource
}