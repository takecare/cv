package io.github.takecare.cv

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
            .map { MainViewModel(it.name, it.githubUsername, it.photoUrl) }
            .observeOn(observeScheduler)
            .subscribeBy(
                onSuccess = { view.show(it) },
                onError = { view.showError(it) }
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
