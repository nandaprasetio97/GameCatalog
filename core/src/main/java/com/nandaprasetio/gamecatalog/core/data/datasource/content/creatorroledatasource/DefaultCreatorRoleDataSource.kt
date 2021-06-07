package com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.CreatorRoleService
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultCreatorRoleDataSource @Inject constructor(
    private val creatorRoleService: CreatorRoleService
): CreatorRoleDataSource {
    override fun getCreatorRoleList(page: Int, pageSize: Int): Single<PagingResult<CreatorRole>> {
        return creatorRoleService.getCreatorRoleList(page, pageSize)
    }
}