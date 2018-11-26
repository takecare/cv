package io.github.takecare.cv.cover

import io.github.takecare.cv.CvRepository
import io.github.takecare.cv.model.Cover
import io.github.takecare.cv.model.CoverItem
import io.github.takecare.cv.model.Cv
import io.reactivex.Single

interface CoverRepository {

    fun cover(): Single<Cover>
}

class CoverRepositoryImpl(private val cvRepository: CvRepository) : CoverRepository {

    override fun cover(): Single<Cover> {
        return cvRepository.cv()
            .map {
                val newItems = it.withListItems()
                Cover(newItems)
            }
    }

    private fun Cv.withListItems(): MutableList<CoverItem> {
        val newItems = cover.items.toMutableList()
        newItems.add(CoverItem.Link(personalUrl, personalUrl)) // FIXME include readable text instead of url? @RUI
        newItems.add(CoverItem.Link(githubUsername, githubUsername)) // TODO transform to proper link @RUI
        return newItems
    }
}
