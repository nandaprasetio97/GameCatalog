package com.nandaprasetio.gamecatalog.core

import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedatasource.DefaultGameDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedeveloperdatasource.DefaultGameDeveloperDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.DefaultGameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.DefaultGameRepository
import com.nandaprasetio.gamecatalog.core.di.module.NetworkModule
import com.nandaprasetio.gamecatalog.core.di.module.NetworkServiceModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameRepositoryTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var defaultGameRepository: DefaultGameRepository

    @Before
    fun init() {
        defaultGameRepository = DefaultGameRepository(
            DefaultGameDataSource(
                NetworkServiceModule().provideGameService(
                    NetworkModule().provideRetrofit()
                )
            )
        )
    }

    @Test
    fun getGameList_hasItem() {
        defaultGameRepository.getGameList(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getGameBasedGameDeveloperList_hasItem() {
        defaultGameRepository.getGameBasedGameDeveloperList("feral-interactive", 1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.forEach { game ->
                    println("${game.name}, ${game.slug} ")
                }
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getGameBasedGenreList_hasItem() {
        defaultGameRepository.getGameBasedGenreList("action", 1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.forEach { game ->
                    println("${game.name}, ${game.slug} ")
                }
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getGameDetail_isItemExists() {
        defaultGameRepository.getGameDetail(3498)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                !it.name.isNullOrBlank()
            }
    }
}