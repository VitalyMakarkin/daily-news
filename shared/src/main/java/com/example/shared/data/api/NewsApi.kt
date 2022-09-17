package com.example.shared.data.api

import com.example.shared.model.network.ArticlesPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlinesArticles(
        @Query("country") country: String
    ): Response<ArticlesPageResponse>
}