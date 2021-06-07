package com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring

fun String.toImageUrlString(): ImageUrlString {
    return ImageUrlString(this)
}