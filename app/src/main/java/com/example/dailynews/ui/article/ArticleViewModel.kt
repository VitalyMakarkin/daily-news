package com.example.dailynews.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailynews.model.network.ArticleResponse
import javax.inject.Inject

class ArticleViewModel @Inject constructor() : ViewModel() {
    private var _article: MutableLiveData<ArticleResponse> = MutableLiveData()
    val article: LiveData<ArticleResponse> get() = _article

    fun setArticleByKey(key: String) {
        TODO("Load article from database by key: $key")
    }
}