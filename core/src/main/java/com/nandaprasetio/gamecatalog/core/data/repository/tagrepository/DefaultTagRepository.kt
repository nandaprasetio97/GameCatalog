package com.nandaprasetio.gamecatalog.core.data.repository.tagrepository

import com.nandaprasetio.gamecatalog.core.data.datasource.content.tagdatasource.TagDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.Tag
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.TagDetail
import io.reactivex.Single
import javax.inject.Inject

class DefaultTagRepository @Inject constructor(
    private val tagDataSource: TagDataSource
): TagRepository {
    override fun getTagList(page: Int, pageSize: Int): Single<PagingResult<Tag>> {
        return tagDataSource.getTagList(page, pageSize)
    }

    override fun getTagDetail(id: Int): Single<TagDetail> {
        return tagDataSource.getTagDetail(id)
    }
}