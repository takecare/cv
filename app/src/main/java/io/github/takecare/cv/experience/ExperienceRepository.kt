package io.github.takecare.cv.experience

import io.github.takecare.cv.CvRepository
import io.github.takecare.cv.model.Experience
import io.reactivex.Single

interface ExperienceRepository {

    fun experience(): Single<Experience>
}

class ExperienceRepositoryImpl(private val cvRepository: CvRepository) : ExperienceRepository {

    override fun experience(): Single<Experience> {
        return cvRepository.cv()
            .map { it.experience }
    }
}
