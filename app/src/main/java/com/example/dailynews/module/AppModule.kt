package com.example.dailynews.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            this.ignoreUnknownKeys = true
            this.isLenient = false
        }
    }

    //TODO: Create dispatcher module
    @Provides
    @Named("IO")
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}