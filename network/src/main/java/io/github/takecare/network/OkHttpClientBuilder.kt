package io.github.takecare.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

private fun loggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return loggingInterceptor
}

class OkHttpClientBuilder(
    private val loggingInterceptor: HttpLoggingInterceptor = loggingInterceptor()
) {

    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            //.cache() // TODO
            .build()
    }
}
