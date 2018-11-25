package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.github.takecare.network.CvService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [NetworkModule::class, RxModule::class])
class CvModule {

    @Provides
    fun provideCvRepository(cvService: CvService): CvRepository {
        return CvRepositoryImpl(cvService)
    }

    @Provides
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
