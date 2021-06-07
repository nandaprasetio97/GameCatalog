package com.nandaprasetio.gamecatalog.core.data.datasource.content.storedatasource

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.StoreService
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import com.nandaprasetio.gamecatalog.core.domain.entity.store.StoreDetail
import io.reactivex.Single
import javax.inject.Inject

class DefaultStoreDataSource @Inject constructor(
    private val storeService: StoreService
): StoreDataSource {
    override fun getStoreList(page: Int, pageSize: Int): Single<PagingResult<Store>> {
        return storeService.getStoreList(page, pageSize)
    }

    override fun getStoreDetail(id: Int): Single<StoreDetail> {
        return storeService.getStoreDetail(id)
    }
}