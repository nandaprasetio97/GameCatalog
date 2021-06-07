package com.nandaprasetio.gamecatalog.core

import com.nandaprasetio.gamecatalog.core.data.datasource.content.genredatasource.DefaultGenreDataSource
import com.nandaprasetio.gamecatalog.core.data.repository.genrerepository.DefaultGenreRepository
import com.nandaprasetio.gamecatalog.core.di.module.NetworkModule
import com.nandaprasetio.gamecatalog.core.di.module.NetworkServiceModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GenreRepositoryTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var defaultGenreRepository: DefaultGenreRepository

    @Before
    fun init() {
        defaultGenreRepository = DefaultGenreRepository(
            DefaultGenreDataSource(
                NetworkServiceModule().provideGenreService(
                    NetworkModule().provideRetrofit()
                )
            )
        )
    }

    @Test
    fun getGenreList_hasItem() {
        defaultGenreRepository.getGenreList(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                it.result.isNotEmpty()
            }
    }

    @Test
    fun getGenreDetail_isItemExists() {
        defaultGenreRepository.getGenreDetail(70)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue {
                !it.name.isNullOrBlank()
            }
    }
}