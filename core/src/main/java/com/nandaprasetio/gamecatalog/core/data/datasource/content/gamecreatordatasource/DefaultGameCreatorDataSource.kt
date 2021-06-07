package com.nandaprasetio.gamecatalog.core.data.datasource.content.gamecreatordatasource

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.GameCreatorService
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreatorDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGameCreatorDataSource @Inject constructor(
    private val gameCreatorService: GameCreatorService
): GameCreatorDataSource {
    override fun getGameCreatorList(page: Int, pageSize: Int): Single<PagingResult<GameCreator>> {
        return gameCreatorService.getGameCreatorList(page, pageSize)
    }

    override fun getGameCreatorDetail(id: Int): Single<GameCreatorDetail> {
        return gameCreatorService.getGameCreatorDetail(id)
    }
}