package com.nandaprasetio.gamecatalog.core.data.repository.gamerepository

import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedatasource.GameDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGameRepository @Inject constructor(
    private val gameDataSource: GameDataSource
): GameRepository {
    override fun getGameList(page: Int, pageSize: Int): Single<PagingResult<Game>> {
        return gameDataSource.getGameList(page, pageSize)
    }

    override fun getGameDetail(id: Int): Single<GameDetail> {
        return gameDataSource.getGameDetail(id)
    }
}