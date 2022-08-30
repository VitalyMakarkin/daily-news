package com.example.dailynews.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dailynews.model.database.ArticleDB

@Database(entities = [ArticleDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}