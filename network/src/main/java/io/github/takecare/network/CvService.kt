package io.github.takecare.network

import io.github.takecare.network.model.Cv
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

// https://gist.githubusercontent.com/takecare/861509cde97d41a23a1cfa9bb9664b41/raw/

interface CvService {

    @GET("{user}/{gistId}/raw/{revision}")
    fun getCv(
        @Path("user") username: String,
        @Path("gistId") gistId: String,
        @Path("revision") revision: String
    ): Single<Cv>
}

