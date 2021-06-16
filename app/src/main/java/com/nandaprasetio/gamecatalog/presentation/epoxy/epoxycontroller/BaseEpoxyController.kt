package com.nandaprasetio.gamecatalog.presentation.epoxy.epoxycontroller

import com.airbnb.epoxy.EpoxyController
import com.nandaprasetio.gamecatalog.presentation.epoxy.EpoxyListParameter
import com.nandaprasetio.gamecatalog.presentation.epoxy.WithListParameterEpoxyController

abstract class BaseEpoxyController: EpoxyController(), WithListParameterEpoxyController {
    override val epoxyListParameter: EpoxyListParameter = EpoxyListParameter { this.requestModelBuild() }
}