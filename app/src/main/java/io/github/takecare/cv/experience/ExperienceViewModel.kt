package io.github.takecare.cv.experience

data class ExperienceViewModel(
        val items: List<ExperienceItemViewModel>
)

data class ExperienceItemViewModel(
        val name: String,
        val logoUrl: String?,
        val role: String,
        val from: String,
        val to: String,
        val description: String
)
