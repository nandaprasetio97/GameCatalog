package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.nandaprasetio.gamecatalog.core.domain.entity.tags.Tag

class TagPagingResult(
    count: Int,
    next: String?,
    previous: String?,
    result: List<Tag>
): BasePagingResult<Tag>(count, next, previous, result)