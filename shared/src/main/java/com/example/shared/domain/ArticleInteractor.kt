package com.example.shared.domain

import com.example.shared.data.ArticleRepository
import com.example.shared.model.database.ArticleDB
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend fun getArticles(country: String): Result<List<ArticleDB>> {
        return articleRepository.getArticles(country)
    }

    suspend fun addFavoriteArticle(id: Int) {
        return articleRepository.addFavoriteArticle(id)
    }

    suspend fun removeFavoriteArticle(id: Int) {
        return articleRepository.removeFavoriteArticle(id)
    }
}