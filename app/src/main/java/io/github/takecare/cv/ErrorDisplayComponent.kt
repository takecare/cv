package io.github.takecare.cv

import dagger.Component
import io.github.takecare.SnackbarDisplayer

@Component(modules = [ErrorDisplayModule::class])
interface ErrorDisplayComponent {

    fun snackbarDisplayer(): SnackbarDisplayer
}
