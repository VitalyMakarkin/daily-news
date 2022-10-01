package com.example.app_compose.ui.articles

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shared.presentation.model.BaseArticleUI

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<BaseArticleUI>
) {
    LazyColumn {
        // items(articles) {}
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleListPreview() {
    ArticlesList(articles = emptyList())
}