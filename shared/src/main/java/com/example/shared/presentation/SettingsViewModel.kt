package com.example.shared.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val articlesInteractor: ArticleInteractor
) : ViewModel() {

    sealed class UiStateView {
        data class Data(
            val articlesCount: Int,
            val cachedArticlesCount: Int,
            val favoriteArticlesCount: Int
        ) : UiStateView()

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

            // TODO: Define articlesCount from articlesInteractor
            var articlesCount = 0
            articlesInteractor.getArticles(preferred_cache = true)
                .onSuccess { articlesCount = it.size }

            // TODO: Define cachedArticlesCount from articlesInteractor
            var cachedArticlesCount = 0
            articlesInteractor.getArticles(preferred_cache = true)
                .onSuccess {
                    cachedArticlesCount = it.filter { article -> !article.isFavorite }.size
                }

            // TODO: Define favoriteArticlesCount from articlesInteractor
            var favoriteArticlesCount = 0
            articlesInteractor.getFavoriteArticles()
                .onSuccess { favoriteArticlesCount = it.size }

            try {
                _uiStateLiveData.value = UiStateView.Loading

                _uiStateLiveData.value = UiStateView.Data(
                    articlesCount = articlesCount,
                    cachedArticlesCount = cachedArticlesCount,
                    favoriteArticlesCount = favoriteArticlesCount
                )

            } catch (error: Throwable) {
                _uiStateLiveData.value = UiStateView.Error(error)
                Timber.w(error)
            }
        }
    }

    fun removeAllArticles() {
        viewModelScope.launch {
            articlesInteractor.getArticles(preferred_cache = true)
                .onSuccess { articles ->
                    // TODO: Show progress bar with counter
                    Timber.d("${articles.size} articles ready to be deleted")
                    articles.map { article ->
                        try {
                            articlesInteractor.removeArticleFromCache(article.id)
                            // TODO: Update progress counter
                            Timber.d("Article ${article.id} has been removed")
                        } catch (error: Exception) {
                            Timber.e("Article ${article.id} has not been removed!")
                        }
                    }
                }
        }.invokeOnCompletion {
            // TODO: Show success as Toast
            refresh()
        }
    }

    fun removeCachedNotFavoriteArticles() {
        viewModelScope.launch {
            articlesInteractor.getNotFavoriteArticles()
                .onSuccess { articles ->
                    Timber.d("${articles.size} cached (not favorite) articles ready to be deleted")
                    articles.map { article ->
                        try {
                            articlesInteractor.removeArticleFromCache(article.id)
                            // TODO: Update progress counter
                            Timber.d("Cached article ${article.id} has been removed")
                        } catch (error: Exception) {
                            Timber.e("Cached article ${article.id} has not been removed!")
                        }
                    }
                }
        }.invokeOnCompletion {
            // TODO: Show success as Toast
            refresh()
        }
    }

    fun removeFavoriteArticles() {
        viewModelScope.launch {
            articlesInteractor.getFavoriteArticles()
                .onSuccess { articles ->
                    Timber.d("${articles.size} articles ready to be removed from favorites")
                    articles.map { article ->
                        try {
                            articlesInteractor.setArticleFavoriteState(article.id, false)
                            // TODO: Update progress counter
                            Timber.d("Favorite article ${article.id} has been removed")
                        } catch (error: Exception) {
                            Timber.e("Favorite article ${article.id} has not been removed!")
                        }
                    }
                }
        }.invokeOnCompletion {
            // TODO: Show success as Toast
            refresh()
        }
    }
}