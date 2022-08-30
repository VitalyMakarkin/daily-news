package com.example.dailynews.domain

import com.example.dailynews.data.ArticleRepository
import com.example.dailynews.model.database.ArticleDB
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend fun getTopHeadlinesArticles(country: String): Result<List<ArticleDB>> {
        return articleRepository.getTopHeadlinesArticles(country)
    }
}