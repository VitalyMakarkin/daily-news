package com.example.shared.domain

import com.example.shared.data.articles.ArticlesRepository
import com.example.shared.model.database.ArticleDB
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val articlesRepository: ArticlesRepository,
) {
    suspend fun getArticles(): Result<List<ArticleDB>> {
        return articlesRepository.getArticles()
    }

    suspend fun getFavoriteArticles(): Result<List<ArticleDB>> {
        return articlesRepository.getArticles().map { articlesList ->
            articlesList.filter { it.favoritesAt != "" }
        }
    }

    suspend fun setArticleFavoriteState(id: Int, isFavorite: Boolean) {
        return articlesRepository.setArticleFavoriteState(id, isFavorite)
    }
}