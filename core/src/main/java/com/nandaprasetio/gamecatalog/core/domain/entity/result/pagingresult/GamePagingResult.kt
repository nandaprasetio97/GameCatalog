package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game

class GamePagingResult(
    count: Int,
    next: String?,
    previous: String?,
    result: List<Game>
): BasePagingResult<Game>(count, next, previous, result)