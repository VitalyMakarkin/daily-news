package com.example.shared.data.articles.cached

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shared.model.database.CachedArticleDB

@Dao
interface CachedArticlesDao {
    @Query("SELECT * FROM cached_articles")
    suspend fun getCachedArticles(): List<CachedArticleDB>

    @Insert(onConflict = REPLACE)
    suspend fun insertCachedArticles(vararg article: CachedArticleDB)
}