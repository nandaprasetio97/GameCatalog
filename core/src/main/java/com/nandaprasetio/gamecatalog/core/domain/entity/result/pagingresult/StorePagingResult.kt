package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.nandaprasetio.gamecatalog.core.domain.entity.store.Store

class StorePagingResult(
    count: Int,
    next: String?,
    previous: String?,
    result: List<Store>
): BasePagingResult<Store>(count, next, previous, result)