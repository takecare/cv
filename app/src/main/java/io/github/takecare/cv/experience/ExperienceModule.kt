package io.github.takecare.cv.experience

import dagger.Module
import dagger.Provides
import io.github.takecare.Background
import io.github.takecare.Foreground
import io.github.takecare.RxModule
import io.github.takecare.cv.CvModule
import io.github.takecare.cv.CvRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [CvModule::class, RxModule::class])
class ExperienceModule {

    @Provides
    @ExperienceScope
    fun provideExperienceRepository(cvRepository: CvRepository): ExperienceRepository {
        return ExperienceRepositoryImpl(cvRepository)
    }

    @Provides
    @ExperienceScope
    fun provideExperiencePresenter(
        experienceRepository: ExperienceRepository,
        disposables: CompositeDisposable,
        @Background backgroundScheduler: Scheduler,
        @Foreground observeScheduler: Scheduler
    ): ExperiencePresenter {

        return ExperiencePresenter(experienceRepository, disposables, backgroundScheduler, observeScheduler)
    }
}
