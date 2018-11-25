package io.github.takecare.cv

import android.util.Log
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class MainPresenter(
        private val cvRepository: CvRepository,
        private val disposables: CompositeDisposable,
        private val backgroundScheduler: Scheduler,
        private val observeScheduler: Scheduler
) {

    private lateinit var view: MainView

    fun startPresenting(view: MainView) {
        this.view = view
        disposables += cvRepository.cv()
                .subscribeOn(backgroundScheduler)
                .observeOn(observeScheduler)
                .map { MainViewModel(it.name, it.githubUsername, it.photoUrl) }
                .subscribeBy(
                        onSuccess = {
                            view.show(it)
                            Log.d("RUI", "$it") // TODO remove logging
                        },
                        onError = {
                            view.showError(it)
                            Log.e("RUI", "$it")
                        }
                )
    }

    fun stopPresenting() {
        disposables.clear()
    }
}

interface MainView {

    fun show(viewModel: MainViewModel)

    // TODO loading state

    fun showError(throwable: Throwable)
}
