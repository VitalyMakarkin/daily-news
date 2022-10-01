package com.example.app_compose.ui.articles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shared.presentation.model.ImageArticleUI

@Composable
fun ImageArticle(
    modifier: Modifier = Modifier,
    articleUI: ImageArticleUI
) {
    // TODO: Implement
}

@Preview(showBackground = true)
@Composable
fun ImageArticlePreview() {
    ImageArticle(
        articleUI = ImageArticleUI(
            id = 1,
            title = "Title",
            publishedAt = "01-01-2022",
            urlToImage = "url",
            isFavorite = true
        )
    )
}