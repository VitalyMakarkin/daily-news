package com.example.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shared.data.db.dao.ArticlesDao
import com.example.shared.data.db.model.ArticleDB

@Database(
    entities = [ArticleDB::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}