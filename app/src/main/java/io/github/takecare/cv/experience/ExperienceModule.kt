package io.github.takecare.cv.experience

import dagger.Module
import dagger.Provides
import io.github.takecare.cv.CvRepository

@Module
class ExperienceModule {

    @Provides
    fun provideExperienceRepository(cvRepository: CvRepository): ExperienceRepositoryImpl {
        return ExperienceRepositoryImpl(cvRepository)
    }
}

