package io.github.takecare.cv

import io.github.takecare.cv.model.*
import io.github.takecare.network.model.Topic
import java.time.Instant
import java.util.*
import io.github.takecare.network.model.Cv as NetworkCv
import io.github.takecare.network.model.Experience as NetworkExperience

fun networkCv(
    name: String = "name",
    githubUsername: String = "ghUsername",
    personalUrl: String = "https://personal.url",
    photoUrl: String = "https://photo.url",
    cover: String = "hello!",
    topics: List<Topic> = listOf(topic()),
    experience: List<NetworkExperience> = listOf(networkExperience())
): NetworkCv {
    return NetworkCv(name, githubUsername, personalUrl, photoUrl, cover, topics, experience)
}

fun networkExperience(
    name: String = "xp",
    logoUrl: String = "https://logo.url",
    role: String = "role",
    start: Date = Date.from(Instant.now()),
    end: Date = Date.from(Instant.now()),
    description: String = "description"
): NetworkExperience {
    return NetworkExperience(name, logoUrl, role, start, end, description)
}

fun topic(name: String = "name", description: String = "description"): Topic {
    return Topic(name, description)
}

fun cv(
    name: String = "name",
    githubUsername: String = "ghUsername",
    personalUrl: String = "https://personal.url",
    photoUrl: String = "https://photo.url",
    cover: Cover = cover(),
    experience: Experience = experience()
): Cv {
    return Cv(
        name,
        photoUrl,
        githubUsername,
        personalUrl,
        cover,
        experience
    )
}

fun cover(): Cover {
    return Cover(
        listOf(
            CoverItem.Letter("cover"),
            CoverItem.Link("link", "link"),
            CoverItem.Knowledge("title", "description")
        )
    )
}

fun experience(): Experience {
    return Experience(
        listOf(
            ExperienceItem(
                "name",
                "logoUrl",
                "role",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "description"
            )
        )
    )
}
