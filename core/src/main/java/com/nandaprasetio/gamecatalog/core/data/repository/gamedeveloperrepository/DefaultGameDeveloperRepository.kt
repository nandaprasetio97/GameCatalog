package com.nandaprasetio.gamecatalog.core.data.repository.gamedeveloperrepository

import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamedeveloperdatasource.GameDeveloperDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloper
import com.nandaprasetio.gamecatalog.core.domain.entity.gamedeveloper.GameDeveloperDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGameDeveloperRepository @Inject constructor(
    private val gameDeveloperDataSource: GameDeveloperDataSource
): GameDeveloperRepository {
    override fun getGameDeveloperList(page: Int, pageSize: Int): Single<PagingResult<GameDeveloper>> {
        return gameDeveloperDataSource.getGameDeveloperList(page, pageSize)
    }

    override fun getGameDeveloperDetail(id: Int): Single<GameDeveloperDetail> {
        return gameDeveloperDataSource.getGameDeveloperDetail(id)
    }
}