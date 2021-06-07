package com.nandaprasetio.gamecatalog.core.data.repository.creatorrolerepository

import com.nandaprasetio.gamecatalog.core.data.datasource.content.creatorroledatasource.CreatorRoleDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultCreatorRoleRepository @Inject constructor(
    private val creatorRoleDataSource: CreatorRoleDataSource
): CreatorRoleRepository {
    override fun getCreatorRoleList(page: Int, pageSize: Int): Single<PagingResult<CreatorRole>> {
        return creatorRoleDataSource.getCreatorRoleList(page, pageSize)
    }
}