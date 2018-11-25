package io.github.takecare.network

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

interface ImageLoader {

    fun load(url: String, target: ImageView)

    fun loadAsCircle(url: String, target: ImageView)
}

class GlideImageLoader(
    private val glide: RequestManager
) : ImageLoader {

    override fun load(url: String, target: ImageView) {
        glide
            .load(url)
            .into(target)
    }

    override fun loadAsCircle(url: String, target: ImageView) {
        val multiTransformation = MultiTransformation(CenterCrop(), CircleCrop())
        glide
            .load(url)
            .apply(RequestOptions.bitmapTransform(multiTransformation))
            .into(target)
    }
}
