package com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult

import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator

class GameCreatorPagingResult(
    count: Int,
    next: String?,
    previous: String?,
    result: List<GameCreator>
): BasePagingResult<GameCreator>(count, next, previous, result)