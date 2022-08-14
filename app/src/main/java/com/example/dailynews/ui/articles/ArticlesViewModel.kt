package com.example.dailynews.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailynews.domain.ArticleInteractor
import com.example.dailynews.model.network.ArticleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
) :
    ViewModel() {
    private var _articles: MutableLiveData<List<ArticleResponse>> = MutableLiveData()
    val articles: LiveData<List<ArticleResponse>> get() = _articles

    init {
        viewModelScope.launch {
            articleInteractor.getTopHeadlinesArticles("ru")
                .onSuccess {
                    _articles.value = it.articles
                }
                .onFailure {
                    TODO("Implement")
                }
        }
    }
}