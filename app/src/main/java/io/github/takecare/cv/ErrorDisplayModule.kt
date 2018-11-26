package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.github.takecare.SnackbarDisplayer

@Module
class ErrorDisplayModule(
    //private val context: Context // view: View
) {

    @Provides
    fun provideSnackbarDisplayer(): SnackbarDisplayer {
        return SnackbarDisplayer()
    }
}
