package com.nandaprasetio.gamecatalog.core.domain.entity.result

sealed class FetchDataResult<T> {
    data class Success<T>(val value: T): FetchDataResult<T>()
    data class Error<T>(val t: Throwable): FetchDataResult<T>()
}