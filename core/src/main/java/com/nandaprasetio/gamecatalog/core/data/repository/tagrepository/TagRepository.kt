package com.nandaprasetio.gamecatalog.core.data.repository.tagrepository

import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.Tag
import com.nandaprasetio.gamecatalog.core.domain.entity.tags.TagDetail
import io.reactivex.Single

interface TagRepository {
    fun getTagList(page: Int, pageSize: Int): Single<PagingResult<Tag>>
    fun getTagDetail(id: Int): Single<TagDetail>
}