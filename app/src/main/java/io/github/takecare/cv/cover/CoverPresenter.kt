package io.github.takecare.cv.cover

import io.github.takecare.cv.model.CoverItem
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class CoverPresenter(
    private val coverRepository: CoverRepository,
    private val linkOpener: LinkOpener,
    private val disposables: CompositeDisposable,
    private val backgroundScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : LinkOpener {

    private lateinit var view: CoverView

    fun startPresenting(view: CoverView) {
        this.view = view
        disposables += coverRepository.cover()
            .subscribeOn(backgroundScheduler)
            .map { cover ->
                val items = cover.items.map {
                    when (it) {
                        // TODO proper drawable
                        is CoverItem.Link -> CoverItemViewModel.Link(it.text, it.url, 0)
                        is CoverItem.Letter -> CoverItemViewModel.Letter(it.text)
                        is CoverItem.Knowledge -> CoverItemViewModel.Knowledge(it.title, it.description)
                    }
                }
                CoverViewModel(items.reversed())
            }
            .observeOn(observeScheduler)
            .subscribeBy(
                onSuccess = { view.show(it) },
                onError = { view.showError(it) }
            )
    }

    override fun openLink(url: String) {
        linkOpener.openLink(url)
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
