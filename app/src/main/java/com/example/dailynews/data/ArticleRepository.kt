package com.example.dailynews.data

import android.accounts.NetworkErrorException
import com.example.dailynews.model.network.ArticlesPageResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

// TODO: Create module
@Singleton
class ArticleRepository @Inject constructor(
    private val newsApi: NewsApi,
    @Named("IO") private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getTopHeadlinesArticles(country: String): Result<ArticlesPageResponse> {
        return withContext(backgroundDispatcher) {
            val response = newsApi.getTopHeadlinesArticles(country)

            try {
                return@withContext response.body()?.let { Result.success(it) }
                    ?: Result.failure(NetworkErrorException())
            } catch (e: Throwable) {
                Result.failure(NetworkErrorException())
            }
        }
    }
}