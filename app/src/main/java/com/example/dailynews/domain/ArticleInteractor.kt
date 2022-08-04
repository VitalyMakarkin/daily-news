package com.example.dailynews.domain

import com.example.dailynews.data.NewsApi
import com.example.dailynews.model.ArticlesPage
import retrofit2.Response
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getTopHeadlinesArticles(country: String, apiKey: String): Response<ArticlesPage> {
        return newsApi.getTopHeadlinesArticles(country, apiKey)
    }
}