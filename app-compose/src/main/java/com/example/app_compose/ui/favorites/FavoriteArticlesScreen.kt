package com.example.app_compose.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app_compose.ui.shared.ArticlesList
import com.example.shared.presentation.FavoriteArticlesViewModel

@Composable
fun FavoriteArticlesScreen(
    viewModel: FavoriteArticlesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.uiStateLiveData.observeAsState()

    when (val uiState = state.value) {
        is FavoriteArticlesViewModel.UiStateView.Data -> {
            ArticlesList(
                articles = uiState.favoriteArticles,
                modifier = modifier,
                // showArticleDetailsAction = ,
                addArticleToFavoritesAction = {},
                removeArticleFromFavoritesAction = {
                    viewModel.removeArticleFromFavorites(it)
                }
            )
        }
        is FavoriteArticlesViewModel.UiStateView.Error -> {
            ErrorScreen(
                message = uiState.throwable.message ?: "Error!",
                modifier = modifier
            ) { viewModel.refresh() }
        }
        is FavoriteArticlesViewModel.UiStateView.Loading -> {
            LoadingScreen(modifier = modifier)
        }
        else -> {} // throw Exception("Not implemented state")
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteArticlesScreenPreview() {
    // TODO: Implement screen preview
}
