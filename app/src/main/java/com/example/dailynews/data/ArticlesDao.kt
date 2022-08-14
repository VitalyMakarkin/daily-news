package com.example.dailynews.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.dailynews.model.database.ArticleModel

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM articles_table")
    suspend fun getArticles(): List<ArticleModel>

    @Insert(onConflict = REPLACE)
    suspend fun insertArticles(vararg article: ArticleModel)
}