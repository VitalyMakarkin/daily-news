package com.example.app_compose.ui.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shared.presentation.SettingsViewModel

@ExperimentalFoundationApi
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.uiStateLiveData.observeAsState()

    var cachedArticlesCount = "-"
    var favoriteArticlesCount = "-"
    var articlesCount = "-"

    when (val uiState = state.value) {
        is SettingsViewModel.UiStateView.Data -> {
            cachedArticlesCount = uiState.cachedArticlesCount.toString()
            favoriteArticlesCount = uiState.favoriteArticlesCount.toString()
            articlesCount = uiState.articlesCount.toString()
        }
        is SettingsViewModel.UiStateView.Error -> {
            cachedArticlesCount = "Error"
            favoriteArticlesCount = "Error"
            articlesCount = "Error"
        }
        is SettingsViewModel.UiStateView.Loading -> {
            cachedArticlesCount = "-"
            favoriteArticlesCount = "-"
            articlesCount = "-"
        }
        else -> {} // throw Exception("Not implemented state")
    }

    Column {
        Column(
            modifier = modifier
                .combinedClickable(
                    onClick = {},
                    onLongClick = { viewModel.removeAllArticles() })
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Total articles:",
                    modifier = modifier
                )
                Text(
                    text = articlesCount,
                    modifier = modifier
                        .padding(start = 4.dp)
                )
            }
            Text(
                text = "Long press to clear",
                modifier = modifier
            )
        }
        Divider()
        Column(
            modifier = modifier
                .combinedClickable(
                    onClick = {},
                    onLongClick = { viewModel.removeCachedNotFavoriteArticles() })
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Cached (not favorite) articles:",
                    modifier = modifier
                )
                Text(
                    text = cachedArticlesCount,
                    modifier = modifier
                        .padding(start = 4.dp)
                )
            }
            Text(
                text = "Long press to clear",
                modifier = modifier
            )
        }
        Divider()
        Column(
            modifier = modifier
                .combinedClickable(
                    onClick = {},
                    onLongClick = { viewModel.removeFavoriteArticles() })
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = "Favorite articles:",
                    modifier = modifier
                )
                Text(
                    text = favoriteArticlesCount,
                    modifier = modifier
                        .padding(start = 4.dp)
                )
            }
            Text(
                text = "Long press to clear",
                modifier = modifier
            )
        }
        Divider()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}