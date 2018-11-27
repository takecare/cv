package io.github.takecare.cv

import dagger.Module
import dagger.Provides
import io.github.takecare.ActivityScope
import io.github.takecare.RxModule
import io.github.takecare.network.CvService
import io.github.takecare.network.NetworkModule

@Module(includes = [NetworkModule::class, RxModule::class])
class CvModule {

    @Provides
    @ActivityScope
    fun provideCvRepository(cvService: CvService): CvRepository {
        return CvRepositoryImpl(cvService)
    }
}
