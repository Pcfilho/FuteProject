package com.androidlearn.example.futeproject.util

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.androidlearn.example.futeproject.R

fun ImageView.loadSvgOrOther(
    myUrl: String?,
    cache: Boolean = true,
    errorImg: Int = R.drawable.loading
) {

    myUrl?.let {
        if (it.lowercase().endsWith("svg")) {
            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry {
                    add(SvgDecoder(this@loadSvgOrOther.context))
                }.build()

            val request = ImageRequest.Builder(this.context).apply {
                error(errorImg)
                placeholder(errorImg)
                data(it).decoder(SvgDecoder(this@loadSvgOrOther.context))
            }.target(this).build()

            imageLoader.enqueue(request)
        } else {
            val imageLoader = ImageLoader(context)

            val request = ImageRequest.Builder(context).apply {
                if (cache) {
                    memoryCachePolicy(CachePolicy.ENABLED)
                } else {
                    memoryCachePolicy(CachePolicy.DISABLED)
                }
                error(errorImg)
                placeholder(errorImg)
                data(it)
            }.target(this).build()

            imageLoader.enqueue(request)
        }
    }
}
