package com.example.shared.presentation.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.model.database.ArticleDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
) :
    ViewModel() {
    private var _articles: MutableLiveData<List<ArticleDB>> = MutableLiveData()
    val articles: LiveData<List<ArticleDB>> get() = _articles

    init {
        viewModelScope.launch {
            articleInteractor.getTopHeadlinesArticles("ru")
                .onSuccess {
                    _articles.value = it
                }
                .onFailure {
                    TODO("Implement")
                }
        }
    }
}