package com.example.app_compose.ui.articles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shared.presentation.model.TextArticleUI

@Composable
fun TextArticle(
    modifier: Modifier = Modifier,
    articleUI: TextArticleUI
) {
    // TODO: Implement
}

@Preview(showBackground = true)
@Composable
fun TextArticlePreview() {
    TextArticle(
        articleUI = TextArticleUI(
            id = 1,
            title = "Title",
            description = "Description",
            publishedAt = "01-01-2022",
            isFavorite = true
        )
    )
}