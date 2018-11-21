package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.github.takecare.network.CvService

@Module
class NetworkModule {

    @Provides
    fun provideCvService(): CvService {
        return CvService()
    }
}
