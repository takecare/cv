package io.github.takecare.cv

import io.github.takecare.cv.model.*
import io.github.takecare.network.CvService
import io.github.takecare.network.model.Topic
import io.reactivex.Single
import io.github.takecare.network.model.Cv as NetworkCv
import io.github.takecare.network.model.Experience as NetworkExperience

interface CvRepository {

    fun cv(): Single<Cv>
}

data class Cached<T>(var value: T? = null)

class CvRepositoryImpl(
    private val cvService: CvService
) : CvRepository {

    private val cache = Cached<Cv>()

    override fun cv(): Single<Cv> {
        cache.value?.let {
            return Single.just(it)
        }
        return fetch()
            .cache()
            .doOnSuccess { cache.value = it }
            .doOnError { cache.value = null }
    }

    private fun fetch(): Single<Cv> {
        return cvService.getCv()
            .map { networkModel ->
                Cv(
                    networkModel.name,
                    networkModel.photoUrl,
                    networkModel.githubUsername,
                    networkModel.personalUrl,
                    networkModel.asCover(),
                    networkModel.asExperience()
                )
            }
    }
}

private fun NetworkCv.asCover(): Cover {
    val items = this.topics.map { it.toCoverItem() }.toMutableList()
    items.add(CoverItem.Letter(this.cover))
    return Cover(items)
}

private fun Topic.toCoverItem(): CoverItem {
    return CoverItem.Knowledge(this.name, this.description)
}

private fun NetworkCv.asExperience(): Experience {
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
