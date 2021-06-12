package com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue

import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.BaseItemModelValue

class CombinationWithItemModelValue<O: BaseItemModelValue>(
    var fetchDataResult: FetchDataResult<O>?
): BaseCompoundModelValue()