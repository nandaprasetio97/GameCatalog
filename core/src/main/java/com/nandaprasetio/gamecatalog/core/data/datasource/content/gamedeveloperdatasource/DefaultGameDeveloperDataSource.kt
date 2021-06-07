package com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedeveloperdatasource

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.GameDeveloperService
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGameDeveloperDataSource @Inject constructor(
    private val gameDeveloperService: GameDeveloperService
): GameDeveloperDataSource {
    override fun getGameDeveloperList(page: Int, pageSize: Int): Single<PagingResult<GameDeveloper>> {
        return gameDeveloperService.getGameDeveloperList(page, pageSize)
    }

    override fun getGameDeveloperDetail(id: Int): Single<GameDeveloperDetail> {
        return gameDeveloperService.getGameDeveloperDetail(id)
    }
}