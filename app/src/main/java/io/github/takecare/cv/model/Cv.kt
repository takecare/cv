package io.github.takecare.cv.model

import java.util.*

data class Cv(
    val name: String,
    val githubUsername: String,
    val personalUrl: String,
    val cover: Cover,
    val experience: Experience
)

data class Cover(
    val items: List<CoverItem>
)

sealed class CoverItem {
    data class Letter(val text: String) : CoverItem()
    data class Link(val text: String, val url: String) : CoverItem()
    data class Knowledge(val title: String, val description: String) : CoverItem()
}

data class Experience(
    val items: List<ExperienceItem>
)

data class ExperienceItem(
    val name: String,
    val logoUrl: String?,
    val role: String,
    val from: Date,
    val to: Date?,
    val description: String
)
