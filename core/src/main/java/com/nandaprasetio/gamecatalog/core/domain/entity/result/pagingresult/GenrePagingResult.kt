package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.nandaprasetio.gamecatalog.core.domain.entity.genre.Genre

class GenrePagingResult(
    count: Int,
    next: String?,
    previous: String?,
    result: List<Genre>
): BasePagingResult<Genre>(count, next, previous, result)