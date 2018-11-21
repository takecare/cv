package io.github.takecare.network

import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class CvServiceModule {

    @Provides
    fun provideCvService(cvRetrofitService: CvRetrofitService): CvService {
        return CvService(cvRetrofitService)
    }
}
