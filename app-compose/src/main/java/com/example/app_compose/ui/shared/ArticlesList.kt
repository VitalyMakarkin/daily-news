package com.example.app_compose.ui.shared

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shared.presentation.model.BaseArticleUI
import com.example.shared.presentation.model.ImageArticleUI
import com.example.shared.presentation.model.TextArticleUI

@Composable
fun ArticlesList(
    articles: List<BaseArticleUI>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(articles) { article ->
            when (article) {
                is TextArticleUI -> TextArticleItem(
                    article = article,
                    modifier = modifier
                )
                is ImageArticleUI -> ImageArticleItem(
                    article = article,
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleListPreview() {
    ArticlesList(articles = emptyList())
}