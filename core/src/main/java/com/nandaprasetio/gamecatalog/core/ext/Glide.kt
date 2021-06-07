package com.nandaprasetio.gamecatalog.core.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.BaseRequestOptions
import com.nandaprasetio.gamecatalog.core.domain.entity.imageurlstring.ImageUrlString

fun ImageView.setImageUrl(url: String?, applyRequestBuilder: (RequestBuilder<Drawable>.() -> Unit)? = null) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .apply { applyRequestBuilder?.invoke(this) }
        .into(this)
}

fun ImageView.setImageUrl(imageUrlString: ImageUrlString?, applyRequestBuilder: (RequestBuilder<Drawable>.() -> Unit)? = null) {
    setImageUrl(imageUrlString?.toString(), applyRequestBuilder)
}