package com.example.shared.presentation.favorites

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
class FavoritesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
): ViewModel() {
    private var _favoriteArticles: MutableLiveData<List<CachedArticleDB>> = MutableLiveData()
    val favoriteArticles: LiveData<List<CachedArticleDB>> get() = _favoriteArticles

    init {
        viewModelScope.launch {
            articleInteractor.getArticles("ru")
                .onSuccess {
                    _favoriteArticles.value = it
                }
                .onFailure {
                    TODO("Implement")
                }
        }
    }
}