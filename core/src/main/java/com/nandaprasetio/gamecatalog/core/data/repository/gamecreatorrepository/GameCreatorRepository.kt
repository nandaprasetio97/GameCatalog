package com.nandaprasetio.gamecatalog.core.data.repository.gamecreatorrepository

import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreator
import com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator.GameCreatorDetail
import com.nandaprasetio.gamecatalog.core.domain.entity.result.pagingresult.PagingResult
import io.reactivex.Single

interface GameCreatorRepository {
    fun getGameCreatorList(page: Int, pageSize: Int): Single<PagingResult<GameCreator>>
    fun getGameCreatorDetail(id: Int): Single<GameCreatorDetail>
}