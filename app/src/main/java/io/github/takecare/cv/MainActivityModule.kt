package io.github.takecare.cv

import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import io.github.takecare.network.GlideImageLoader
import io.github.takecare.network.ImageLoader
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [CvModule::class])
class MainActivityModule(
    private val mainActivity: MainActivity
) {

    @Provides
    @ActivityScope
    fun providePresenter(
        cvRepository: CvRepository,
        disposables: CompositeDisposable,
        @Background backgroundScheduler: Scheduler,
        @Foreground observeScheduler: Scheduler
    ): MainPresenter {

        return MainPresenter(
            cvRepository,
            disposables,
            backgroundScheduler,
            observeScheduler
        )
    }

    // TODO add provision of image loader:
    // it might need a context. if so, this module needs a context passed in via  its constructor
    @Provides
    @ActivityScope
    fun provideImageLoader(): ImageLoader {
        return GlideImageLoader(Glide.with(mainActivity))
    }
}
