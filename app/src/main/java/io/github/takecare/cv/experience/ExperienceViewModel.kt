package io.github.takecare.cv.experience

import java.util.*

data class ExperienceViewModel(
    val items: List<ExperienceItemViewModel>
)

data class ExperienceItemViewModel(
    val name: String,
    val logoUrl: String?,
    val role: String,
    val from: Date,
    val to: Date?,
    val description: String
)
