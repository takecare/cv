package io.github.takecare.network.model

import com.squareup.moshi.Json
import java.util.*

data class Cv(
        val name: String,
        @field:Json(name = "github") val githubUsername: String,
        @field:Json(name = "webpage") val personalUrl: String,
        @field:Json(name = "avatar") val photoUrl: String,
        val cover: String,
        @field:Json(name = "knowledge") val topics: List<Topic>,
        val experience: List<Experience>
)

data class Topic(
        val name: String,
        val description: String
)

data class Experience(
        val name: String,
        val logoUrl: String,
        val role: String,
        val from: Date,
        val to: Date,
        val description: String
)
