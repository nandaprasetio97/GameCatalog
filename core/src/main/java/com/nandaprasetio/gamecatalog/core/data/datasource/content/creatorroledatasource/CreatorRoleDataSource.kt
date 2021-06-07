package com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource

import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single

interface CreatorRoleDataSource {
    fun getCreatorRoleList(page: Int, pageSize: Int): Single<PagingResult<CreatorRole>>
}