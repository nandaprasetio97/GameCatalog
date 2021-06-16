package com.nandaprasetio.gamecatalog.core.domain.usecase.gamedetailusecase

import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameDetail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

interface GameDetailUseCase {
    fun getGameDetail(
        id: Int,
        compositeDisposable: CompositeDisposable,
        onSuccess: Consumer<GameDetail>,
        onFailed: Consumer<Throwable>
    )
}