package com.example.dailynews.domain

import com.example.dailynews.data.ArticleRepository
import com.example.dailynews.model.network.ArticlesPageResponse
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend fun getTopHeadlinesArticles(country: String): Result<ArticlesPageResponse> {
        return articleRepository.getTopHeadlinesArticles(country)
    }
}