package com.example.shared.data.articles

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shared.model.database.ArticleDB

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleDB>

    @Insert(onConflict = REPLACE)
    suspend fun insertArticles(article: ArticleDB)

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Int): ArticleDB

    @Query("DELETE FROM articles WHERE id = :id")
    suspend fun removeArticle(id: Int)

    @Query("SELECT id FROM articles WHERE author = :author AND title = :title AND publishedAt = :publishedAt")
    suspend fun checkArticleExists(
        author: String,
        title: String,
        publishedAt: String
    ): Int?

    @Query("UPDATE articles SET favoritesAt = :favoritesAt WHERE id = :id ")
    suspend fun updateArticleFavoritesAt(id: Int, favoritesAt: String)
}