package com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.carouselmodelvalue

import com.nandaprasetio.gamecatalog.core.domain.entity.result.FetchDataResult
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.compoundmodelvalue.BaseCompoundModelValue
import com.nandaprasetio.gamecatalog.core.presentation.modelvalue.itemmodelvalue.BaseItemModelValue

class CarouselModelValue<T: BaseItemModelValue>(
    var title: String?,
    var description: String?,
    var fetchDataResult: FetchDataResult<List<T>>?
): BaseCompoundModelValue()