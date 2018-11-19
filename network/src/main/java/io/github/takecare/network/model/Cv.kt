package io.github.takecare.network.model

import com.squareup.moshi.Json
import java.util.*

/*
{
  "name": "my name",
  "github": "takecare",
  "webpage": "https://robohash.org/webpage",
  "cover": "cover letter?",
  "knowledge": [
    {
      "name": "a thing",
      "description": "very good"
    },
    {
      "name": "another thing",
      "description": "so, so"
    },
    {
      "name": "something else",
      "description": "not so good"
    }
  ],
  "experience": [
    {
      "name": "company",
      "logo": "https://robohash.org/company",
      "role": "a dev",
      "from": "01-01-2016",
      "to": "01-02-2016",
      "description": "working as a dev"
    },
    {
      "name": "another company",
      "logo": "https://robohash.org/another",
      "role": "the dev",
      "from": "02-02-2016",
      "to": "14-06-2016",
      "description": "working as a that guy dev"
    }
  ]
}
*/

data class Cv(
        val name: String,
        @field:Json(name = "github") val githubUsername: String,
        @field:Json(name = "webpage") val personalUrl: String,
        val cover: String,
        @field:Json(name = "knowledge") val topcis: List<Topic>,
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
