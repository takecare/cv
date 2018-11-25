package io.github.takecare.network

import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideCvService(): CvService {
        return CvServiceImpl()
    }
}
