package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.github.takecare.Background
import io.github.takecare.Foreground
import io.github.takecare.network.ImageLoaderModule
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [CvModule::class, ImageLoaderModule::class])
class TestMainActivityModule /*: MainActivityModule()*/ {

    @Provides
    @MainActivityScope
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
}
