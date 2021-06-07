package com.nandaprasetio.gamecatalog.core

import com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource.DefaultCreatorRoleDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource.DefaultCreatorRoleDataSource_Factory
import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamecreatordatasource.DefaultGameCreatorDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.DefaultCreatorRoleRepository
import com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository.DefaultCreatorRoleRepository_Factory
import com.nandaprasetio.gamecatalog.core.di.module.NetworkModule
import com.nandaprasetio.gamecatalog.core.di.module.NetworkServiceModule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreatorRolesRepositoryTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var defaultCreatorRoleRepository: DefaultCreatorRoleRepository

    @Before
    fun init() {
        defaultCreatorRoleRepository = DefaultCreatorRoleRepository(
            DefaultCreatorRoleDataSource(
                NetworkServiceModule().provideCreatorRoleService(
                    NetworkModule().provideRetrofit()
                )
            )
        )
    }

    @Test
    fun getCreatorRolesList_hasItem() {
        defaultCreatorRoleRepository.getCreatorRoleList(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.isNotEmpty()
            }
    }
}