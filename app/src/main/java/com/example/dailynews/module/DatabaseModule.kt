package com.example.dailynews.module

import android.content.Context
import androidx.room.Room
import com.example.dailynews.data.AppDatabase
import com.example.dailynews.data.ArticlesDao
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
    fun provideArticlesDao(db: AppDatabase): ArticlesDao = db.getArticlesDao()
}
