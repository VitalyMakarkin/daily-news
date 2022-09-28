package com.example.shared.domain

import com.example.shared.data.repository.ArticlesRepository
import com.example.shared.domain.model.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Named

class ArticleInteractor @Inject constructor(
    private val articlesRepository: ArticlesRepository,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getArticles(preferred_cache: Boolean): Result<List<Article>> {
        return articlesRepository.getArticles(preferred_cache)
    }

    suspend fun getFavoriteArticles(): Result<List<Article>> {
        return articlesRepository.getArticles(preferred_cache = true)
            .map { articlesList ->
                articlesList.filter { it.isFavorite }
            }
    }

    suspend fun getNotFavoriteArticles(): Result<List<Article>> {
        return articlesRepository.getArticles(preferred_cache = true)
            .map { articlesList ->
                articlesList.filter { !it.isFavorite }
            }
    }

    suspend fun setArticleFavoriteState(id: Int, isFavorite: Boolean) {
        return articlesRepository.setArticleFavoriteState(id, isFavorite)
    }

    suspend fun removeArticleFromCache(id: Int) {
        return articlesRepository.removeArticle(id)
    }
}