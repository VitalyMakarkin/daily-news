package com.example.shared.data.favorite_articles

import com.example.shared.model.database.FavoriteArticleDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class FavoriteArticlesRepository @Inject constructor(
    private val favoriteArticlesDao: FavoriteArticlesDao,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getArticles(): Result<List<FavoriteArticleDB>> {
        return withContext(backgroundDispatcher) {
            val articlesDatabase = favoriteArticlesDao.getFavoriteArticles()
            Result.success(articlesDatabase)
        }
    }

    suspend fun addFavoriteArticle(id: Int) {
        TODO("Add favorite article by id:$id from cache")
    }

    suspend fun removeFavoriteArticle(id: Int) {
        TODO("Remove article by id:$id from favorites")
    }
}