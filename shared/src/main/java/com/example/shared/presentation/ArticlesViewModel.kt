package com.example.shared.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import com.example.shared.presentation.model.ArticleUI
import com.example.shared.presentation.model.mapToUI
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
        data class Data(val articles: List<ArticleUI>) : UiStateView()
        object Loading : UiStateView()
        class Error(val throwable: Throwable) : UiStateView()
    }

    sealed class Event {
        class Error(val throwable: Throwable) : Event()
    }

    private var _uiStateLiveData: MutableLiveData<UiStateView> =
        MutableLiveData(UiStateView.Loading)
    val uiStateLiveData: LiveData<UiStateView> get() = _uiStateLiveData

    private var _eventLiveData: MutableLiveData<Event> = MutableLiveData()
    val eventLiveData: LiveData<Event> get() = _eventLiveData

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiStateLiveData.value = UiStateView.Loading

            try {
                articleInteractor.getArticles(preferred_cache = false)
                    .onSuccess { articles ->
                        val articlesUI = articles.map { it.mapToUI() }
                        _uiStateLiveData.value = UiStateView.Data(articlesUI)
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

    fun resetEvent() {
        _eventLiveData.value = null
    }

    fun eventText(text: String) {
        _eventLiveData.value = Event.Error(Exception("test"))
    }

    fun addArticleToFavorites(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.setArticleFavoriteState(id, true)
            } catch (error: Throwable) {
                _eventLiveData.value = Event.Error(error)
                Timber.w(error)
            }
        }
    }

    fun removeArticleFromFavorites(id: Int) {
        viewModelScope.launch {
            try {
                articleInteractor.setArticleFavoriteState(id, false)
            } catch (error: Throwable) {
                _eventLiveData.value = Event.Error(error)
                Timber.w(error)
            }
        }
    }
}