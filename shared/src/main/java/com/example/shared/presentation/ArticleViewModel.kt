package com.example.shared.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.presentation.model.ArticleDetailsUI
import com.example.shared.presentation.model.mapToDetailsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleInteractor: ArticleInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val ARG_ARTICLE_ID = "arg-article-id"
    }

    private val articleId: Int = requireNotNull(savedStateHandle[ARG_ARTICLE_ID]) {
        "Undefined article ID"
    }

    sealed class UiStateView {
        data class Data(val article: ArticleDetailsUI) : UiStateView()
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
                val result = articleInteractor.getArticle(articleId)
                result
                    .onSuccess {
                        val article = it.mapToDetailsUI()
                        _uiStateLiveData.value = UiStateView.Data(article)
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
}