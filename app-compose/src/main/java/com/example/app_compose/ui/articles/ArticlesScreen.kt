package com.example.app_compose.ui.articles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app_compose.ui.shared.ArticlesList
import com.example.shared.presentation.ArticlesViewModel

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.uiStateLiveData.observeAsState()

    when (val uiState = state.value) {
        is ArticlesViewModel.UiStateView.Data -> {
            ArticlesList(
                articles = uiState.articles,
                modifier = modifier
            )
        }
        is ArticlesViewModel.UiStateView.Error -> {
            ErrorScreen(
                message = uiState.throwable.message ?: "Error!",
                modifier = modifier
            ) { viewModel.refresh() }
        }
        is ArticlesViewModel.UiStateView.Loading -> {
            LoadingScreen(modifier = modifier)
        }
        else -> {} // throw Exception("Not implemented state")
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlesScreenPreview() {
    // TODO: Implement screen preview
}
