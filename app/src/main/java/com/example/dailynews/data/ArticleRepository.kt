package com.example.dailynews.data

import android.accounts.NetworkErrorException
import com.example.dailynews.model.database.ArticleDB
import com.example.dailynews.model.network.mapToDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton // TODO: Create module
class ArticleRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val articlesDao: ArticlesDao,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getTopHeadlinesArticles(country: String): Result<List<ArticleDB>> {
        return withContext(backgroundDispatcher) {
            val articlesDatabase = articlesDao.getArticles()

            if (articlesDatabase.isEmpty()) {
                val response = newsApi.getTopHeadlinesArticles(country)

                try {
                    response.body()?.let {
                        val articles = it.articles.map { article -> article.mapToDatabase() }
                        articlesDao.insertArticles(*articles.toTypedArray())
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
}