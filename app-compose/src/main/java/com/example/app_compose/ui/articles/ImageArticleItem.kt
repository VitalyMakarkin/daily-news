package com.example.app_compose.ui.articles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shared.presentation.model.ImageArticleUI

@Composable
fun ImageArticleItem(
    modifier: Modifier = Modifier,
    article: ImageArticleUI
) {
    Text(modifier = modifier, text = article.title)
}

@Preview(showBackground = true)
@Composable
fun ImageArticleItemPreview() {
    ImageArticleItem(
        article = ImageArticleUI(
            id = 1,
            title = "Title",
            publishedAt = "01-01-2022",
            urlToImage = "url",
            isFavorite = true
        )
    )
}