package com.example.dailynews.domain

import com.example.dailynews.data.ArticleRepository
import com.example.dailynews.data.NewsApi
import com.example.dailynews.model.ArticlesPage
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend fun getTopHeadlinesArticles(country: String): Result<ArticlesPage> {
        return articleRepository.getTopHeadlinesArticles(country)
    }
}