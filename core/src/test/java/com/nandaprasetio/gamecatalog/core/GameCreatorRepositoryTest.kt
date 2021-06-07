package com.nandaprasetio.gamecatalog.core

import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamecreatordatasource.DefaultGameCreatorDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository.DefaultGameCreatorRepository
import com.nandaprasetio.gamecatalog.core.di.module.NetworkModule
import com.nandaprasetio.gamecatalog.core.di.module.NetworkServiceModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameCreatorRepositoryTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var defaultGameCreatorRepository: DefaultGameCreatorRepository

    @Before
    fun init() {
        defaultGameCreatorRepository = DefaultGameCreatorRepository(
            DefaultGameCreatorDataSource(
                NetworkServiceModule().provideGameCreatorService(
                    NetworkModule().provideRetrofit()
                )
            )
        )
    }

    @Test
    fun getGameCreatorList_hasItem() {
        defaultGameCreatorRepository.getGameCreatorList(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getGameCreatorDetail_isItemExists() {
        defaultGameCreatorRepository.getGameCreatorDetail(70)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                !it.name.isNullOrBlank()
            }
    }
}