package io.github.takecare.cv

import dagger.Module

@Module(includes = [CvModule::class])
class MainActivityModule {

    //@Provides
    fun provideMainPresenter() {
        TODO()
    }

    // TODO add provision of image loader:
    // it might need a context. if so, this module needs a context passed in via  its constructor
}
