package com.example.dailynews.module

import com.example.dailynews.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val requestWithAuth = request.newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.NEWSAPI_KEY}")
                    .build()
                it.proceed(requestWithAuth)
            }
            .addInterceptor {
                Timber.d(it.request().url().toString())
                it.proceed(it.request())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")

        @OptIn(ExperimentalSerializationApi::class)
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}