package com.example.shared.data.cached_articles

import android.accounts.NetworkErrorException
import com.example.shared.data.NewsApi
import com.example.shared.model.database.CachedArticleDB
import com.example.shared.model.network.mapToDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton // TODO: Create module
class CachedArticlesRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val articlesDao: CachedArticlesDao,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getArticles(country: String): Result<List<CachedArticleDB>> {
        return withContext(backgroundDispatcher) {
            val articlesDatabase = articlesDao.getCachedArticles()

            if (articlesDatabase.isEmpty()) {
                val response = newsApi.getTopHeadlinesArticles(country)

                try {
                    response.body()?.let {
                        val articles = it.articles.map { article -> article.mapToDatabase() }
                        articlesDao.insertCachedArticles(*articles.toTypedArray())
                        Result.success(articles)
                    }
                        ?: Result.failure(NetworkErrorException())
                } catch (e: Throwable) {
                    Result.failure(NetworkErrorException())
                }
            } else {
                Result.success(articlesDatabase)
            }
        }
    }

    suspend fun addFavoriteArticle(id: Int) {
        TODO("Add favorite article by id:$id from cache")
    }

    suspend fun removeFavoriteArticle(id: Int) {
        TODO("Remove article by id:$id from favorites")
    }
}