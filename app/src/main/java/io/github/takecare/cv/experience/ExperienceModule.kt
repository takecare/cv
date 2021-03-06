package io.github.takecare.cv.experience

import dagger.Module
import dagger.Provides
import io.github.takecare.Background
import io.github.takecare.Foreground
import io.github.takecare.cv.CvRepository
import io.github.takecare.network.ImageLoader
import io.github.takecare.network.ImageLoaderModule
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [ImageLoaderModule::class])
class ExperienceModule {

    @Provides
    fun provideExperienceAdapter(imageLoader: ImageLoader): ExperienceAdapter {
        return ExperienceAdapter(imageLoader)
    }

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
