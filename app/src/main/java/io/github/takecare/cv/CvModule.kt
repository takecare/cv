package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.github.takecare.network.CvService

@Module(includes = [NetworkModule::class])
class CvModule {

    @Provides
    fun provideCvRepository(cvService: CvService): CvRepository {
        return CvRepositoryImpl(cvService)
    }
}
