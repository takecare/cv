package io.github.takecare.cv

import dagger.Component
import io.github.takecare.SnackbarDisplayer
import io.github.takecare.cv.cover.CoverScope

@Component(modules = [ErrorDisplayModule::class])
@CoverScope
interface ErrorDisplayComponent {

    fun snackbarDisplayer(): SnackbarDisplayer
}
