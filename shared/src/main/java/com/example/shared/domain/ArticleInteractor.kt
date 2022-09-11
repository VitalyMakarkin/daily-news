package com.example.shared.domain

import com.example.shared.data.articles.ArticlesRepository
import com.example.shared.model.database.ArticleDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ArticleInteractor @Inject constructor(
    private val articlesRepository: ArticlesRepository,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
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

    suspend fun removeCachedArticles() {
        return withContext(backgroundDispatcher) {
            articlesRepository.getArticles().map { articlesList ->
                articlesList
                    .filter { it.favoritesAt == "" }
                    .map { articlesRepository.removeArticle(it.id) }
            }
        }
    }

    suspend fun removeFavoriteArticles() {
        return withContext(backgroundDispatcher) {
            articlesRepository.getArticles().map { articlesList ->
                articlesList
                    .filter { it.favoritesAt != "" }
                    .map { articlesRepository.setArticleFavoriteState(it.id, false) }
            }
        }
    }
}