package com.example.shared.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.data.db.model.ArticleDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
) :
    ViewModel() {

    sealed class UiStateView {
        data class Data(val articles: List<ArticleDB>) : UiStateView()
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

            try {
                articleInteractor.getArticles(preferred_cache = false)
                    .onSuccess {
                        _uiStateLiveData.value = UiStateView.Data(it)
                    }
                    .onFailure {
                        _uiStateLiveData.value = UiStateView.Error(it)
                        Timber.w(it)
                    }
            } catch (error: Throwable) {
                _uiStateLiveData.value = UiStateView.Error(error)
                Timber.w(error)
            }
        }
    }

    fun addArticleToFavorites(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.setArticleFavoriteState(id, true)
                // TODO: Update ui state as Success
            } catch (error: Throwable) {
                // TODO: Update ui state as Error (example: Toast)
            }
        }
    }

    fun removeArticleFromFavorites(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.setArticleFavoriteState(id, false)
                // TODO: Update ui state as Success
            } catch (error: Throwable) {
                // TODO: Update ui state as Error (example: Toast)
            }
        }
    }
}