package com.nandaprasetio.gamecatalog.core.data.datasource.content.tagdatasource

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.TagService
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.Tag
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.TagDetail
import io.reactivex.Single
import javax.inject.Inject

class DefaultTagDataSource @Inject constructor(
    private val tagService: TagService
): TagDataSource {
    override fun getTagList(page: Int, pageSize: Int): Single<PagingResult<Tag>> {
        return tagService.getTagList(page, pageSize)
    }

    override fun getTagDetail(id: Int): Single<TagDetail> {
        return tagService.getTagDetail(id)
    }
}