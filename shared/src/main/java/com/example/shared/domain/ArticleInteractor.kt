package com.example.shared.domain

import com.example.shared.data.cached_articles.CachedArticlesRepository
import com.example.shared.data.favorite_articles.FavoriteArticlesRepository
import com.example.shared.model.database.CachedArticleDB
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articlesRepository: CachedArticlesRepository,
    private val favoriteArticleRepository: FavoriteArticlesRepository
) {
    suspend fun getArticles(country: String): Result<List<CachedArticleDB>> {
        return articlesRepository.getArticles(country)
    }

    suspend fun addFavoriteArticle(id: Int) {
        return favoriteArticleRepository.addFavoriteArticle(id)
    }

    suspend fun removeFavoriteArticle(id: Int) {
        return favoriteArticleRepository.removeFavoriteArticle(id)
    }
}