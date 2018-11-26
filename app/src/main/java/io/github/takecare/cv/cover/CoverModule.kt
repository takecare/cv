package io.github.takecare.cv.cover

import android.app.Activity
import android.support.customtabs.CustomTabsIntent
import dagger.Module
import dagger.Provides
import io.github.takecare.Background
import io.github.takecare.Foreground
import io.github.takecare.cv.CvModule
import io.github.takecare.cv.CvRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [CvModule::class])
class CoverModule(private val activity: Activity) {

    @Provides
    @CoverScope
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @CoverScope
    fun providerCoverAdapter(linkOpener: LinkOpener): CoverAdapter {
        return CoverAdapter(linkOpener)
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
