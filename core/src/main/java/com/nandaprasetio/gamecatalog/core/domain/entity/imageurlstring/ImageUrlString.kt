package com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring

import androidx.core.net.toUri

class ImageUrlString(
    private val imageUrl: String?,
    private val imageWidth: Int = 600,
    private val imageHeight: Int = 400
) {
    override fun toString(): String {
        return imageUrl?.toUri()?.let { uri ->
            val stringBuilder: StringBuilder = StringBuilder()
            stringBuilder.append("${uri.scheme}://")
            stringBuilder.append(uri.host)
            for (i in uri.pathSegments.indices) {
                stringBuilder.append("/${uri.pathSegments[i]}")
                if (i == 0) {
                    stringBuilder.append("/crop/${imageWidth}/${imageHeight}")
                }
            }
            stringBuilder.toString()
        } ?: ""
    }
}