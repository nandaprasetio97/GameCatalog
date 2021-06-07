package com.nandaprasetio.gamecatalog.core.di.module

import com.nandaprasetio.gamecatalog.core.domain.usecase.creatorroleusecase.CreatorRoleUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.creatorroleusecase.DefaultCreatorRoleUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamecreatorusecase.DefaultGameCreatorUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamecreatorusecase.GameCreatorUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperusecase.DefaultGameDeveloperUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gamedeveloperusecase.GameDeveloperUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gameusecase.DefaultGameUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.gameusecase.GameUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.genreusecase.DefaultGenreUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.genreusecase.GenreUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.storeusecase.DefaultStoreUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.storeusecase.StoreUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.tagusecase.DefaultTagUseCase
import com.nandaprasetio.gamecatalog.core.domain.usecase.tagusecase.TagUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun provideDefaultCreatorRoleUseCase(defaultCreatorRoleUseCase: DefaultCreatorRoleUseCase): CreatorRoleUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDefaultGameCreatorUseCase(defaultGameCreatorUseCase: DefaultGameCreatorUseCase): GameCreatorUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDefaultGameDeveloperUseCase(defaultGameDeveloperUseCase: DefaultGameDeveloperUseCase): GameDeveloperUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDefaultGameUseCase(defaultGameUseCase: DefaultGameUseCase): GameUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDefaultGenreUseCase(defaultGenreUseCase: DefaultGenreUseCase): GenreUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDefaultStoreUseCase(defaultStoreUseCase: DefaultStoreUseCase): StoreUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDefaultTagUseCase(defaultTagUseCase: DefaultTagUseCase): TagUseCase
}