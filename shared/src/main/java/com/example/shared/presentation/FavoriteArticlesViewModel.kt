package com.example.shared.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.model.database.ArticleDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteArticlesViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor
) :
    ViewModel() {

    sealed class UiStateView {
        data class Data(val favoriteArticles: List<ArticleDB>) : UiStateView()
        object Loading : UiStateView()
        class Error(val throwable: Throwable) : UiStateView()
    }

    private var _uiStateLiveData: MutableLiveData<UiStateView> =
        MutableLiveData(UiStateView.Loading)
    val uiStateView: LiveData<UiStateView> get() = _uiStateLiveData

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiStateLiveData.value = UiStateView.Loading

            try {
                articleInteractor.getFavoriteArticles()
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