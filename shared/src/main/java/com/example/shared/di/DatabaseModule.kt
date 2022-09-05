package com.example.shared.di

import android.content.Context
import androidx.room.Room
import com.example.shared.data.AppDatabase
import com.example.shared.data.articles.cached.CachedArticlesDao
import com.example.shared.data.articles.favorite.FavoriteArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "articles.db"
        ).build()

    @Provides
    @Singleton
    fun provideCachedArticlesDao(db: AppDatabase): CachedArticlesDao =
        db.getCachedArticlesDao()

    @Provides
    @Singleton
    fun provideFavoriteArticlesDao(db: AppDatabase): FavoriteArticlesDao =
        db.getFavoriteArticlesDao()
}
