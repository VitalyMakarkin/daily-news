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

        object Loading : UiStateView()
        class Error(val throwable: Throwable) : UiStateView()
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
            val articlesCount = 0

            // TODO: Define cachedArticlesCount from articlesInteractor
            val cachedArticlesCount = 0

            // TODO: Define favoriteArticlesCount from articlesInteractor
            val favoriteArticlesCount = 0

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

    fun removeCachedArticles() {
        viewModelScope.launch {
            articlesInteractor.removeCachedArticles()
            // TODO: Show success as Toast
        }
    }

    fun removeFavoriteArticles() {
        viewModelScope.launch {
            articlesInteractor.removeFavoriteArticles()
            // TODO: Show success as Toast
        }
    }
}