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

data class Cached<T>(var value: T? = null)

class CvRepositoryImpl(
    private val cvService: CvService
) : CvRepository {

    private val cache = Cached<Cv>()

    override fun cv(): Single<Cv> {
        val toCache = cvService.getCv()
            .map { networkModel ->
                Cv(
                    networkModel.name,
                    networkModel.githubUsername,
                    networkModel.personalUrl,
                    networkModel.cover(),
                    networkModel.experience()
                )
            }
            .cache()
        return cache(toCache)
    }

    private fun cache(single: Single<Cv>): Single<Cv> {
        cache.value?.let {
            return Single.just(it)
        }
        return single
            .doOnSuccess { cache.value = it }
            .doOnError { cache.value = null }
    }
}

private fun NetworkCv.cover(): Cover {
    val items = this.topcis.map { it.toCoverItem() }.toMutableList()
    items.add(CoverItem.Letter(this.cover))
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
