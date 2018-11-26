package io.github.takecare.cv

import android.content.Context
import dagger.Module
import dagger.Provides

// TODO delete @RUI
@Module
class ContextModule(
    private val context: Context
) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}
