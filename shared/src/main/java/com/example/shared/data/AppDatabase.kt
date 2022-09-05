package com.example.shared.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shared.data.articles.cached.CachedArticlesDao
import com.example.shared.data.articles.favorite.FavoriteArticlesDao
import com.example.shared.model.database.CachedArticleDB
import com.example.shared.model.database.FavoriteArticleDB

@Database(
    entities = [
        CachedArticleDB::class,
        FavoriteArticleDB::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCachedArticlesDao(): CachedArticlesDao
    abstract fun getFavoriteArticlesDao(): FavoriteArticlesDao
}