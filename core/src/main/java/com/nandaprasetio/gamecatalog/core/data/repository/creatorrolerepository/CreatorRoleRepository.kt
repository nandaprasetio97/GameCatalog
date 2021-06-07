package com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository

import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single

interface CreatorRoleRepository {
    fun getCreatorRoleList(page: Int, pageSize: Int): Single<PagingResult<CreatorRole>>
}