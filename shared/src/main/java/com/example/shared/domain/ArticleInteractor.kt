package com.example.shared.domain

import com.example.shared.data.articles.cached.CachedArticlesRepository
import com.example.shared.data.articles.favorite.FavoriteArticlesRepository
import com.example.shared.model.database.CachedArticleDB
import com.example.shared.model.database.FavoriteArticleDB
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articlesRepository: CachedArticlesRepository,
    private val favoriteArticleRepository: FavoriteArticlesRepository
) {
    suspend fun getArticles(country: String): Result<List<CachedArticleDB>> {
        return articlesRepository.getArticles(country)
    }

    suspend fun getFavoriteArticles(): Result<List<FavoriteArticleDB>> {
        return favoriteArticleRepository.getArticles()
    }

    suspend fun addFavoriteArticle(id: Int) {
        val article = articlesRepository.getArticleById(id)
        // TODO: Map article to favorite article
        // TODO: Pass favorite article as param
        return favoriteArticleRepository.addArticle(id)
    }

    suspend fun removeFavoriteArticle(id: Int) {
        return favoriteArticleRepository.removeArticle(id)
    }
}