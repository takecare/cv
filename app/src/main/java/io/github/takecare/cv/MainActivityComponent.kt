package io.github.takecare.cv

import dagger.Component
import io.github.takecare.ActivityScope
import io.github.takecare.Background
import io.github.takecare.Foreground
import io.github.takecare.SnackbarDisplayer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@ActivityScope
@Component(modules = [MainActivityModule::class, ErrorDisplayModule::class])
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    fun snackbarDisplayer(): SnackbarDisplayer

    fun compositeDisposable(): CompositeDisposable

    @Background fun backgroundScheduler(): Scheduler

    @Foreground fun foregroundScheduler(): Scheduler

    fun cvRepository(): CvRepository
}
