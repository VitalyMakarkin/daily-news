package com.example.shared.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.model.database.FavoriteArticleDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
) : ViewModel() {
    private var _favoriteArticles: MutableLiveData<List<FavoriteArticleDB>> = MutableLiveData()
    val favoriteArticles: LiveData<List<FavoriteArticleDB>> get() = _favoriteArticles

    init {
        viewModelScope.launch {
            articleInteractor.getFavoriteArticles()
                .onSuccess {
                    _favoriteArticles.value = it
                }
                .onFailure {
                    TODO("Implement")
                }
        }
    }

    fun removeFavoriteArticle(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.removeFavoriteArticle(id)
            } catch (error: Throwable) {
                // TODO: Show Toast with error message
            }
        }
    }
}