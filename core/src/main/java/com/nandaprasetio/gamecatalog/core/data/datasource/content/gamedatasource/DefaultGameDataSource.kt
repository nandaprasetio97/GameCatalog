package com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedatasource

import com.nandaprasetio.gamecatalog.core.data.datasource.restapi.service.GameService
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGameDataSource @Inject constructor(
    private val gameService: GameService
): GameDataSource {
    override fun getGameList(page: Int, pageSize: Int): Single<PagingResult<Game>> {
        return gameService.getGameList(page, pageSize)
    }

    override fun getGameBasedGameDeveloperList(slug: String?, page: Int, pageSize: Int): Single<PagingResult<Game>> {
        return gameService.getGameBasedGameDeveloperList(slug, page, pageSize)
    }

    override fun getGameBasedGenreList(slug: String?, page: Int, pageSize: Int): Single<PagingResult<Game>> {
        return gameService.getGameBasedGenreList(slug, page, pageSize)
    }

    override fun getGameDetail(id: Int): Single<GameDetail> {
        return gameService.getGameDetail(id)
    }
}