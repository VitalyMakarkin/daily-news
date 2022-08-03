package com.example.dailynews.ui.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailynews.BuildConfig
import com.example.dailynews.data.NewsApi
import com.example.dailynews.model.Article
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor() : ViewModel() {
    private var _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles get() = _articles

    private val json = Json {
        this.coerceInputValues = true
        this.ignoreUnknownKeys = true
        this.isLenient = false
    }

    private val contentType = MediaType.get("application/json")

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(NewsApi::class.java)

    private val apiKey = BuildConfig.NEWSAPI_KEY

    init {
        viewModelScope.launch {
            _articles.value =
                retrofit.getTopHeadlinesArticles("ru", apiKey)
                    .body()!!
                    .articles
        }
    }
}