package io.github.takecare.cv.cover

import io.github.takecare.cv.CvRepository
import io.github.takecare.cv.model.Cover
import io.reactivex.Single

interface CoverRepository {

    fun cover(): Single<Cover>
}

class CoverRepositoryImpl(private val cvRepository: CvRepository) : CoverRepository {

    override fun cover(): Single<Cover> {
        return cvRepository.cv()
            .map { it.cover }
    }
}
