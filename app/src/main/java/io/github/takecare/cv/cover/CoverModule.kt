package io.github.takecare.cv.cover

import dagger.Module
import dagger.Provides
import io.github.takecare.Background
import io.github.takecare.cv.CvModule
import io.github.takecare.cv.CvRepository
import io.github.takecare.Foreground
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [CvModule::class])
class CoverModule {

    @Provides
    @CoverScope
    fun provideCoverRepository(cvRepository: CvRepository): CoverRepository {
        return CoverRepositoryImpl(cvRepository)
    }

    @Provides
    @CoverScope
    fun provideCoverPresenter(
            coverRepository: CoverRepository,
            disposables: CompositeDisposable,
            @Background backgroundScheduler: Scheduler,
            @Foreground observeScheduler: Scheduler
    ): CoverPresenter {

        return CoverPresenter(
                coverRepository,
                disposables,
                backgroundScheduler,
                observeScheduler
        )
    }
}
