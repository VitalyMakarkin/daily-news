package com.example.shared.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.ArticleInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val articlesInteractor: ArticleInteractor
) : ViewModel() {

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