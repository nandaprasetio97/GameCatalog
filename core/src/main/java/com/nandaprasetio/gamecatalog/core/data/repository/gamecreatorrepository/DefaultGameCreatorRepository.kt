package com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository

import com.nandaprasetio.gamecatalog.core.data.datasource.content.gamecreatordatasource.GameCreatorDataSource
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreatorDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single
import javax.inject.Inject

class DefaultGameCreatorRepository @Inject constructor(
    private val gameCreatorDataSource: GameCreatorDataSource
): GameCreatorRepository {
    override fun getGameCreatorList(page: Int, pageSize: Int): Single<PagingResult<GameCreator>> {
        return gameCreatorDataSource.getGameCreatorList(page, pageSize)
    }

    override fun getGameCreatorDetail(id: Int): Single<GameCreatorDetail> {
        return gameCreatorDataSource.getGameCreatorDetail(id)
    }
}