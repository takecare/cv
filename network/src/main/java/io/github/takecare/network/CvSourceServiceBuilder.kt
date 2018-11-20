package io.github.takecare.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val BASE_URL = "https://gist.githubusercontent.com/"

private fun okHttpClient(): OkHttpClient {
    return OkHttpClientBuilder().build()
}

private fun moshi(): Moshi {
    return Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()
}

class CvSourceServiceBuilder(
    private val okHttpClient: OkHttpClient = okHttpClient(),
    private val moshi: Moshi = moshi(),
    private val baseUrl: String = BASE_URL
) {

    fun build(): CvRetrofitService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create<CvRetrofitService>(CvRetrofitService::class.java)
    }
}
