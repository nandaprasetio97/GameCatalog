package com.nandaprasetio.gamecatalog.core.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.ImageUrlString

private fun ImageView.setAnyImage(image: Any?, applyRequestBuilder: (RequestBuilder<Drawable>.() -> Unit)? = null) {
    Glide.with(this)
        .load(image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .apply { applyRequestBuilder?.invoke(this) }
        .into(this)
}

fun ImageView.setImageUrl(url: String?, applyRequestBuilder: (RequestBuilder<Drawable>.() -> Unit)? = null) {
    setAnyImage(url, applyRequestBuilder)
}

fun ImageView.setImageUrl(imageUrlString: ImageUrlString?, applyRequestBuilder: (RequestBuilder<Drawable>.() -> Unit)? = null) {
    setImageUrl(imageUrlString?.toString(), applyRequestBuilder)
}

fun ImageView.setImage(image: Any?, applyRequestBuilder: (RequestBuilder<Drawable>.() -> Unit)? = null) {
    when (image) {
        is String -> setImageUrl(image, applyRequestBuilder)
        is ImageUrlString -> setImageUrl(image, applyRequestBuilder)
        else -> setAnyImage(image, applyRequestBuilder)
    }
}