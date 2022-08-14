package com.example.dailynews.domain

import com.example.dailynews.data.ArticleRepository
import com.example.dailynews.model.database.ArticleModel
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend fun getTopHeadlinesArticles(country: String): Result<List<ArticleModel>> {
        return articleRepository.getTopHeadlinesArticles(country)
    }
}