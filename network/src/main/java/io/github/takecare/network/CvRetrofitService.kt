package io.github.takecare.network

import io.github.takecare.network.model.Cv
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CvRetrofitService {

    @GET("{user}/{gistId}/raw/{revision}")
    fun getRevision(
        @Path("user") username: String,
        @Path("gistId") gistId: String,
        @Path("revision") revision: String
    ): Single<Cv>

    @GET("{user}/{gistId}/raw/{fileName}")
    fun getLatest(
        @Path("user") username: String,
        @Path("gistId") gistId: String,
        @Path("fileName") fileName: String
    ): Single<Cv>
}
