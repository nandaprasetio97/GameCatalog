package com.nandaprasetio.gamecatalog.core.data.repository.storerepository

import com.nandaprasetio.gamecatalog.core.data.datasource.content.storedatasource.StoreDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import com.nandaprasetio.gamecatalog.core.domain.entity.store.StoreDetail
import io.reactivex.Single
import javax.inject.Inject

class DefaultStoreRepository @Inject constructor(
    private val storeDataSource: StoreDataSource
): StoreRepository {
    override fun getStoreList(page: Int, pageSize: Int): Single<PagingResult<Store>> {
        return storeDataSource.getStoreList(page, pageSize)
    }

    override fun getStoreDetail(id: Int): Single<StoreDetail> {
        return storeDataSource.getStoreDetail(id)
    }
}