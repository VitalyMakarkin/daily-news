package com.example.shared.data.repository

import android.accounts.NetworkErrorException
import com.example.shared.data.api.NewsApi
import com.example.shared.data.api.mapper.mapToDatabase
import com.example.shared.data.db.dao.ArticlesDao
import com.example.shared.data.db.model.ArticleDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val articlesDao: ArticlesDao,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getArticles(preferred_cache: Boolean): Result<List<ArticleDB>> {
        return withContext(backgroundDispatcher) {

            if (!preferred_cache) {
                val response = newsApi.getTopHeadlinesArticles("ru")

                try {
                    response.body()?.let {
                        val articles = it.articles.map { article -> article.mapToDatabase() }

                        articles.map { article ->
                            val articleId = articlesDao.checkArticleExists(
                                article.author, article.title, article.publishedAt
                            )
                            if (articleId == null) articlesDao.insertArticles(article)
                        }
                    } ?: Result.failure<Throwable>(NetworkErrorException())
                } catch (e: Throwable) {
                    Result.failure<Throwable>(NetworkErrorException())
                }
            }

            val articlesDB = articlesDao.getArticles()
            Result.success(articlesDB)
        }
    }

    suspend fun getArticleById(id: Int) {
        return withContext(backgroundDispatcher) {
            try {
                val articleDB = articlesDao.getArticleById(id)
                Result.success(articleDB)
            } catch (error: Throwable) {
                Result.failure(NotImplementedError())
            }
        }
    }

    suspend fun setArticleFavoriteState(id: Int, isFavorite: Boolean) {
        val articleFavoriteAt = when {
            isFavorite -> LocalDateTime.now().toString()
            else -> ""
        }
        articlesDao.updateArticleFavoritesAt(id, articleFavoriteAt)
    }

    suspend fun removeArticle(id: Int) {
        articlesDao.removeArticle(id)
    }
}