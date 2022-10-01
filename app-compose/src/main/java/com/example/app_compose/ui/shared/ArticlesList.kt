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
    modifier: Modifier = Modifier,
    articles: List<BaseArticleUI>
) {
    LazyColumn {
        items(articles) { article ->
            when (article) {
                is TextArticleUI -> TextArticleItem(
                    modifier = modifier,
                    article = article
                )
                is ImageArticleUI -> ImageArticleItem(
                    modifier = modifier,
                    article = article
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