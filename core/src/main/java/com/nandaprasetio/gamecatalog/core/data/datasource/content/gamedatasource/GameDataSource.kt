package com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedatasource

import com.nandaprasetio.gamecatalog.core.domain.entity.game.Game
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single

interface GameDataSource {
    fun getGameList(page: Int, pageSize: Int): Single<PagingResult<Game>>
    fun getGameBasedGameDeveloperList(slug: String?, page: Int, pageSize: Int): Single<PagingResult<Game>>
    fun getGameDetail(id: Int): Single<GameDetail>
}