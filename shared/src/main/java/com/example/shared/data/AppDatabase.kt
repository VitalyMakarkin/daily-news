package com.example.shared.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shared.data.articles.ArticlesDao
import com.example.shared.model.database.ArticleDB

@Database(
    entities = [ArticleDB::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}