package com.nandaprasetio.gamecatalog.core.data.datasource.content.storedatasource

import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store
import com.nandaprasetio.gamecatalog.core.domain.entity.store.StoreDetail
import io.reactivex.Single

interface StoreDataSource {
    fun getStoreList(page: Int, pageSize: Int): Single<PagingResult<Store>>
    fun getStoreDetail(id: Int): Single<StoreDetail>
}