package com.example.shared.data.favorite_articles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shared.model.database.FavoriteArticleDB

@Dao
interface FavoriteArticlesDao {
    @Query("SELECT * FROM favorite_articles")
    suspend fun getFavoriteArticles(): List<FavoriteArticleDB>

    @Insert(onConflict = REPLACE)
    suspend fun insertFavoriteArticles(vararg article: FavoriteArticleDB)
}