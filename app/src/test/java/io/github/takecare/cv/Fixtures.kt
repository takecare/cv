package io.github.takecare.cv

import io.github.takecare.network.model.Cv
import io.github.takecare.network.model.Experience
import io.github.takecare.network.model.Topic
import java.time.Instant
import java.util.*

fun networkCv(
    name: String = "name",
    githubUsername: String = "ghUsername",
    personalUrl: String = "https://personal.url",
    photoUrl: String = "https://photo.url",
    cover: String = "hello!",
    topics: List<Topic> = listOf(topic()),
    experience: List<Experience> = listOf(experience())
): Cv {
    return Cv(name, githubUsername, personalUrl, photoUrl, cover, topics, experience)
}

fun experience(
    name: String = "xp",
    logoUrl: String = "https://logo.url",
    role: String = "role",
    start: Date = Date.from(Instant.now()),
    end: Date = Date.from(Instant.now()),
    description: String = "description"
): Experience {
    return Experience(name, logoUrl, role, start, end, description)
}

fun topic(name: String = "name", description: String = "description"): Topic {
    return Topic(name, description)
}
