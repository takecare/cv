package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [CvModule::class])
class MainActivityModule {

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
}
