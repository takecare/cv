package io.github.takecare.cv

import dagger.Subcomponent
import io.github.takecare.SnackbarDisplayer

@Subcomponent(modules = [ErrorDisplayModule::class])
interface ErrorDisplayComponent {

    fun snackbarDisplayer(): SnackbarDisplayer

    //    fun inject(activity: MainActivity)
    @Subcomponent.Builder
    interface Builder {
        fun errorDisplayModule(module: ErrorDisplayModule): Builder
        fun build(): ErrorDisplayComponent
    }
}
