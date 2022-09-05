package com.example.shared.presentation.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.model.database.CachedArticleDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
) :
    ViewModel() {
    private var _articles: MutableLiveData<List<CachedArticleDB>> = MutableLiveData()
    val articles: LiveData<List<CachedArticleDB>> get() = _articles

    init {
        viewModelScope.launch {
            articleInteractor.getArticles("ru")
                .onSuccess {
                    _articles.value = it
                }
                .onFailure {
                    TODO("Implement")
                }
        }
    }

    fun addArticleToFavorite(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.addFavoriteArticle(id)
                // TODO: Update ui state as Success
            } catch (error: Throwable) {
                // TODO: Update ui state as Error (example: Toast)
            }
        }
    }

    fun removeArticleFromFavorite(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.removeFavoriteArticle(id)
                // TODO: Update ui state as Success
            } catch (error: Throwable) {
                // TODO: Update ui state as Error (example: Toast)
            }
        }
    }
}