package com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository

import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single

interface GameDeveloperRepository {
    fun getGameDeveloperList(page: Int, pageSize: Int): Single<PagingResult<GameDeveloper>>
    fun getGameDeveloperDetail(id: Int): Single<GameDeveloperDetail>
}