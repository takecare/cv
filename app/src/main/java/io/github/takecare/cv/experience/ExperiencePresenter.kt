package io.github.takecare.cv.experience

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class ExperiencePresenter(
    private val experienceRepository: ExperienceRepository,
    private val disposables: CompositeDisposable,
    private val backgroundScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    private lateinit var view: ExperienceView

    fun startPresenting(view: ExperienceView) {
        this.view = view
        disposables += experienceRepository.experience()
            .subscribeOn(backgroundScheduler)
            .map { experience ->
                val items = experience.items.map {
                    ExperienceItemViewModel(
                        it.name,
                        it.logoUrl,
                        it.role,
                        it.from,
                        it.to,
                        it.description
                    )
                }
                ExperienceViewModel(items.reversed())
            }
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

interface ExperienceView {

    fun show(experienceViewModel: ExperienceViewModel)

    // TODO loading state

    fun showError(throwable: Throwable)
}
