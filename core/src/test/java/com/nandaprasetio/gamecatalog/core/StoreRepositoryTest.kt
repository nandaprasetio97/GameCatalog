package com.nandaprasetio.gamecatalog.core

import com.nandaprasetio.gamecatalog.core.data.datasource.content.genredatasource.DefaultGenreDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.storedatasource.DefaultStoreDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.DefaultGenreRepository
import com.nandaprasetio.gamecatalog.core.data.repository.storerepository.DefaultStoreRepository
import com.nandaprasetio.gamecatalog.core.di.module.NetworkModule
import com.nandaprasetio.gamecatalog.core.di.module.NetworkServiceModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StoreRepositoryTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var defaultStoreRepository: DefaultStoreRepository

    @Before
    fun init() {
        defaultStoreRepository = DefaultStoreRepository(
            DefaultStoreDataSource(
                NetworkServiceModule().provideStoreService(
                    NetworkModule().provideRetrofit()
                )
            )
        )
    }

    @Test
    fun getStoreList_hasItem() {
        defaultStoreRepository.getStoreList(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getStoreDetail_isItemExists() {
        defaultStoreRepository.getStoreDetail(70)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                !it.name.isNullOrBlank()
            }
    }
}