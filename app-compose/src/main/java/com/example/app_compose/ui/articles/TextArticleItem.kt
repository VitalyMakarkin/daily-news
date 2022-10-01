package com.example.app_compose.ui.articles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shared.presentation.model.TextArticleUI

@Composable
fun TextArticleItem(
    modifier: Modifier = Modifier,
    article: TextArticleUI
) {
    Text(modifier = modifier, text = article.title)
}

@Preview(showBackground = true)
@Composable
fun TextArticleItemPreview() {
    TextArticleItem(
        article = TextArticleUI(
            id = 1,
            title = "Title",
            description = "Description",
            publishedAt = "01-01-2022",
            isFavorite = true
        )
    )
}