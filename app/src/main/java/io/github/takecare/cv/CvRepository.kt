package io.github.takecare.cv

import io.github.takecare.cv.model.*
import io.github.takecare.network.CvService
import io.github.takecare.network.model.Topic
import io.reactivex.Single
import io.github.takecare.network.model.Cv as NetworkCv
import io.github.takecare.network.model.Experience as NetworkExperience

// TODO caching (in-memory lru cache)
// TODO map from biz classes to view model ones <- this should be done in/from the presenter instead @RUI

interface CvRepository {

    fun cv(): Single<Cv>
}

class CvRepositoryImpl(private val cvService: CvService) : CvRepository {

    override fun cv(): Single<Cv> {
        return cvService.getCv()
            .map { networkModel ->
                Cv(
                    networkModel.name,
                    networkModel.githubUsername,
                    networkModel.personalUrl,
                    networkModel.cover(),
                    networkModel.experience()
                )
            }
    }
}

private fun NetworkCv.cover(): Cover {
    val items = this.topcis.map { it.toCoverItem() }.toMutableList()
    items.add(CoverItem.Letter(this.cover))
    items.add(CoverItem.Link(this.personalUrl, this.personalUrl)) // FIXME text
    items.add(CoverItem.Link(this.githubUsername, this.githubUsername)) // TODO transform to proper link
    return Cover(items)
}

private fun Topic.toCoverItem(): CoverItem {
    return CoverItem.Knowledge(this.name, this.description)
}

private fun NetworkCv.experience(): Experience {
    val items = this.experience.map {
        ExperienceItem(
            it.name,
            it.logoUrl,
            it.role,
            it.from,
            it.to,
            it.description
        )
    }
    return Experience(items)
}
