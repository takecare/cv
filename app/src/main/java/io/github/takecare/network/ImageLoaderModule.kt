package io.github.takecare.network

import android.content.Context
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import io.github.takecare.ActivityScope

@Module
class ImageLoaderModule(
    private val context: Context
) {

    @Provides
    @ActivityScope
    fun provideImageLoader(): ImageLoader {
        return GlideImageLoader(Glide.with(context))
    }
}
