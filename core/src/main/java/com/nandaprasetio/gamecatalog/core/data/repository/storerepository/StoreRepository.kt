package com.nandaprasetio.gamecatalog.core.data.repository.storerepository

import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import com.nandaprasetio.gamecatalog.core.domain.entity.store.StoreDetail
import io.reactivex.Single

interface StoreRepository {
    fun getStoreList(page: Int, pageSize: Int): Single<PagingResult<Store>>
    fun getStoreDetail(id: Int): Single<StoreDetail>
}