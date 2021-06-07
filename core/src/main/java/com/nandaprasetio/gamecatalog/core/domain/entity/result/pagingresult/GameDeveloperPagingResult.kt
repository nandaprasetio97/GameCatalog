package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper

class GameDeveloperPagingResult(
    count: Int,
    next: String?,
    previous: String?,
    result: List<GameDeveloper>
): BasePagingResult<GameDeveloper>(count, next, previous, result)