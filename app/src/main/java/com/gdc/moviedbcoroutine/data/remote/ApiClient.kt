package com.gdc.moviedbcoroutine.data.remote

import com.gdc.moviedbcoroutine.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/"
    val theMovieDbApi: TheMovieDbApi
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var original = chain.request()
                        val httpUrl = original.url
                            .newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .build()

                        original = original.newBuilder()
                            .url(httpUrl)
                            .build()

                        return chain.proceed(original)
                    }
                }).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(TheMovieDbApi::class.java)
        }
}