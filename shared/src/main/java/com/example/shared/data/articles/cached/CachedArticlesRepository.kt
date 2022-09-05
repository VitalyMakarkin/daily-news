package com.example.shared.data.articles.cached

import android.accounts.NetworkErrorException
import com.example.shared.data.articles.NewsApi
import com.example.shared.model.database.CachedArticleDB
import com.example.shared.model.network.mapToDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CachedArticlesRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val articlesDao: CachedArticlesDao,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getArticles(country: String): Result<List<CachedArticleDB>> {
        return withContext(backgroundDispatcher) {
            val articlesDB = articlesDao.getCachedArticles()

            if (articlesDB.isEmpty()) {
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
                Result.success(articlesDB)
            }
        }
    }

    suspend fun getArticleById(id: Int) {
        return withContext(backgroundDispatcher) {
            try {
                val articleDB = articlesDao.getCachedArticleById(id)
                Result.success(articleDB)
            } catch (error: Throwable) {
                // TODO: Change error type
                Result.failure(UnknownError())
            }
        }
    }
}