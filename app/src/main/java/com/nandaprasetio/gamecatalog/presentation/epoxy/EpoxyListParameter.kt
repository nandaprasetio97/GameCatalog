package com.nandaprasetio.gamecatalog.presentation.epoxy

import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.BaseModelValue

class EpoxyListParameter(
    private val onRequestModelBuild: () -> Unit
) {
    var parallelFetchDataResultMutableList: Map<String, BaseModelValue> = mapOf()
    var errorFetchDataResult: FetchDataResult.Error<*>? = null
    var loading: Boolean = false
        set(value) {
            field = value
            onRequestModelBuild()
        }
}