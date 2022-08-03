package com.example.dailynews.data

import com.example.dailynews.model.ArticlesPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlinesArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ArticlesPage>
}