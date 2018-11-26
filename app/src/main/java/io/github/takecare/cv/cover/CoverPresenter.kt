package io.github.takecare.cv.cover

import io.github.takecare.cv.model.CoverItem
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class CoverPresenter(
    private val coverRepository: CoverRepository,
    private val disposables: CompositeDisposable,
    private val backgroundScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    private lateinit var view: CoverView

    fun startPresenting(view: CoverView) {
        this.view = view
        disposables += coverRepository.cover()
            .map { cover ->
                val items = cover.items.map {
                    when (it) {
                        is CoverItem.Link -> CoverItemViewModel.Link(it.text, it.url, 0) // TODO proper drawable @RUI
                        is CoverItem.Letter -> CoverItemViewModel.Letter(it.text)
                        is CoverItem.Knowledge -> CoverItemViewModel.Knowledge(it.title, it.description)
                    }
                }
                CoverViewModel(items.reversed())
            }
            .subscribeOn(backgroundScheduler)
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

interface CoverView {

    fun show(coverViewModel: CoverViewModel)

    // TODO loading state

    fun showError(throwable: Throwable)
}
