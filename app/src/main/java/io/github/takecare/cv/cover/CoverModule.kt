package io.github.takecare.cv.cover

import dagger.Module
import dagger.Provides
import io.github.takecare.cv.CvRepository

@Module
class CoverModule {

    @Provides
    fun provideCoverRepository(cvRepository: CvRepository): CoverRepository {
        return CoverRepositoryImpl(cvRepository)
    }
}
