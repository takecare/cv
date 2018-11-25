package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@Module
class RxModule(
        private val backgroundScheduler: Scheduler = Schedulers.io(),
        private val observeScheduler: Scheduler = AndroidSchedulers.mainThread()
) {

    @Provides
    @Background
    fun provideBackgroundScheduler(): Scheduler {
        return backgroundScheduler
    }

    @Provides
    @Foreground
    fun provideObserveScheduler(): Scheduler {
        return observeScheduler
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
