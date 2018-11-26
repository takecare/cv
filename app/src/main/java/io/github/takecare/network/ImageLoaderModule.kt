package io.github.takecare.network

import android.content.Context
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides

@Module
class ImageLoaderModule(
    private val context: Context
) {

    @Provides
    fun provideImageLoader(): ImageLoader {
        return GlideImageLoader(Glide.with(context))
    }
}
