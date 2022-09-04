package com.example.shared.domain

import com.example.shared.data.ArticleRepository
import com.example.shared.model.database.ArticleDB
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend fun getTopHeadlinesArticles(country: String): Result<List<ArticleDB>> {
        return articleRepository.getTopHeadlinesArticles(country)
    }
}