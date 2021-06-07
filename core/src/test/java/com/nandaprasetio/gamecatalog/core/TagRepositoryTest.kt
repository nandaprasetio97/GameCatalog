package com.nandaprasetio.gamecatalog.core

import com.nandaprasetio.gamecatalog.core.data.datasource.content.storedatasource.DefaultStoreDataSource
import com.nandaprasetio.gamecatalog.core.data.datasource.content.tagdatasource.DefaultTagDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.storerepository.DefaultStoreRepository
import com.nandaprasetio.gamecatalog.core.data.repository.tagrepository.DefaultTagRepository
import com.nandaprasetio.gamecatalog.core.di.module.NetworkModule
import com.nandaprasetio.gamecatalog.core.di.module.NetworkServiceModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TagRepositoryTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var defaultTagRepository: DefaultTagRepository

    @Before
    fun init() {
        defaultTagRepository = DefaultTagRepository(
            DefaultTagDataSource(
                NetworkServiceModule().provideTagService(
                    NetworkModule().provideRetrofit()
                )
            )
        )
    }

    @Test
    fun getTagList_hasItem() {
        defaultTagRepository.getTagList(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getTagDetail_isItemExists() {
        defaultTagRepository.getTagDetail(70)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                !it.name.isNullOrBlank()
            }
    }
}