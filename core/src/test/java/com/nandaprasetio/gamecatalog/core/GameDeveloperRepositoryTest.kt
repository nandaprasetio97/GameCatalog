package com.nandaprasetio.gamecatalog.core

import com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource.DefaultCreatorRoleDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedeveloperdatasource.DefaultGameDeveloperDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.DefaultCreatorRoleRepository
import com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository.DefaultGameDeveloperRepository
import com.nandaprasetio.gamecatalog.core.di.module.NetworkModule
import com.nandaprasetio.gamecatalog.core.di.module.NetworkServiceModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameDeveloperRepositoryTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var defaultGameDeveloperRepository: DefaultGameDeveloperRepository

    @Before
    fun init() {
        defaultGameDeveloperRepository = DefaultGameDeveloperRepository(
            DefaultGameDeveloperDataSource(
                NetworkServiceModule().provideGameDeveloperService(
                    NetworkModule().provideRetrofit()
                )
            )
        )
    }

    @Test
    fun getGameDeveloperList_hasItem() {
        defaultGameDeveloperRepository.getGameDeveloperList(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getGameDeveloperDetail_isItemExists() {
        defaultGameDeveloperRepository.getGameDeveloperDetail(70)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                !it.name.isNullOrBlank()
            }
    }
}