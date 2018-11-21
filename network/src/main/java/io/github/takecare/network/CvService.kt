package io.github.takecare.network

import io.github.takecare.network.model.Cv
import io.reactivex.Single

private const val USERNAME = "takecare"
private const val GIST_ID = "861509cde97d41a23a1cfa9bb9664b41"
private const val FILE_NAME = "cv.json"

private fun retrofitService(): CvRetrofitService {
    return CvSourceServiceBuilder().build()
}

class CvService(
        private val cvRetrofitService: CvRetrofitService = retrofitService(),
        private val username: String = USERNAME,
        private val gistId: String = GIST_ID,
        private val fileName: String = FILE_NAME
) {

    fun getCv(): Single<Cv> {
        return cvRetrofitService.getLatest(username, gistId, fileName)
    }
}
