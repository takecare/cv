package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.github.takecare.SnackbarDisplayer

@Module
class ErrorDisplayModule {

    @Provides
    fun provideSnackbarDisplayer(): SnackbarDisplayer {
        return SnackbarDisplayer()
    }
}
