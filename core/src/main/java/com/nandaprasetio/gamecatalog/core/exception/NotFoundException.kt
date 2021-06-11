package com.nandaprasetio.gamecatalog.core.exception

import java.lang.RuntimeException

class NotFoundException: RuntimeException {
    constructor(): super()
    constructor(name: String): super(name)
    constructor(name: String, cause: Exception): super(name, cause)
}