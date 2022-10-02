package com.example.shared.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.presentation.model.BaseArticleUI
import com.example.shared.presentation.model.mapToUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteArticlesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
) : ViewModel() {

    sealed class UiStateView {
        data class Data(val favoriteArticles: List<BaseArticleUI>) : UiStateView()
        class Error(val throwable: Throwable) : UiStateView()
        object Loading : UiStateView()
    }

    private var _uiStateLiveData: MutableLiveData<UiStateView> =
        MutableLiveData(UiStateView.Loading)
    val uiStateLiveData: LiveData<UiStateView> get() = _uiStateLiveData

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiStateLiveData.value = UiStateView.Loading

            try {
                articleInteractor.getFavoriteArticles().onSuccess { articles ->
                    val sortedArticles = articles.sortedByDescending { it.favoritesAt }
                    val articlesUI = sortedArticles.map { it.mapToUI() }
                    _uiStateLiveData.value = UiStateView.Data(articlesUI)
                }.onFailure {
                    _uiStateLiveData.value = UiStateView.Error(it)
                    Timber.w(it)
                }
            } catch (error: Throwable) {
                _uiStateLiveData.value = UiStateView.Error(error)
                Timber.w(error)
            }
        }
    }

    fun removeArticleFromFavorites(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.setArticleFavoriteState(id, false)
            } catch (error: Throwable) {
                Timber.w(error)
            }
        }.invokeOnCompletion { removeArticleFromList(id) }
    }

    private fun removeArticleFromList(id: Int) {
        viewModelScope.launch {
            try {
                if (uiStateLiveData.value is UiStateView.Data) {
                    articleInteractor.getArticle(id).onSuccess { updatedArticle ->
                        val updatedArticleUI = updatedArticle.mapToUI()
                        val articles = (uiStateLiveData.value as UiStateView.Data).favoriteArticles
                            .filter { it.id != updatedArticleUI.id }
                        _uiStateLiveData.value = UiStateView.Data(articles)
                    }
                }
            } catch (error: Throwable) {
                Timber.w(error)
            }
        }
    }
}