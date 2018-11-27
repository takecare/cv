package io.github.takecare.cv.cover

import android.app.Activity
import android.support.customtabs.CustomTabsIntent
import dagger.Module
import dagger.Provides
import io.github.takecare.Background
import io.github.takecare.Foreground
import io.github.takecare.cv.CvRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module
class CoverModule(private val activity: Activity) {

    @Provides
    @CoverScope
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @CoverScope
    fun provideIntentBuilder(): CustomTabsIntent.Builder {
        return CustomTabsIntent.Builder()
    }

    @Provides
    @CoverScope
    fun provideLinkOpener(activity: Activity, intentBuilder: CustomTabsIntent.Builder): LinkOpener {
        return CustomTabsLinkOpener(intentBuilder, activity)
    }

    @Provides
    @CoverScope
    fun provideCoverRepository(cvRepository: CvRepository): CoverRepository {
        return CoverRepositoryImpl(cvRepository)
    }

    @Provides
    @CoverScope
    fun provideCoverPresenter(
        coverRepository: CoverRepository,
        linkOpener: LinkOpener,
        disposables: CompositeDisposable,
        @Background backgroundScheduler: Scheduler,
        @Foreground observeScheduler: Scheduler
    ): CoverPresenter {

        return CoverPresenter(
            coverRepository,
            linkOpener,
            disposables,
            backgroundScheduler,
            observeScheduler
        )
    }
}
