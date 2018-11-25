package io.github.takecare.cv.cover

import io.github.takecare.cv.CvRepository
import io.github.takecare.cv.model.Cover
import io.github.takecare.cv.model.CoverItem
import io.reactivex.Single

interface CoverRepository {

    fun cover(): Single<Cover>
}

class CoverRepositoryImpl(private val cvRepository: CvRepository) : CoverRepository {

    override fun cover(): Single<Cover> {
        return cvRepository.cv()
            .map {
                val newItems = it.cover.items.toMutableList()
                newItems.add(
                    CoverItem.Link(
                        it.personalUrl,
                        it.personalUrl
                    )
                ) // FIXME include readable text instead of url?
                newItems.add(CoverItem.Link(it.githubUsername, it.githubUsername)) // TODO transform to proper link
                Cover(newItems)
            }
    }
}
