package com.nandaprasetio.gamecatalog.core.domain.usecase.gamedetailusecase

import com.nandaprasetio.gamecatalog.core.data.repository.gamerepository.GameRepository
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameDetail
import com.nandaprasetio.gamecatalog.core.ext.wrapSingle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DefaultGameDetailUseCase @Inject constructor(
    private val gameRepository: GameRepository
): GameDetailUseCase {
    override fun getGameDetail(
        id: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<GameDetail>,
        onFailed: Consumer<Throwable>
    ) {
        return gameRepository.getGameDetail(id)
            .wrapSingle()
            .subscribeWithComposeDisposableAndCallback(compositeDisposable, onSuccess, onFailed)
    }
}