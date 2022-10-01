package com.example.app_compose.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shared.presentation.model.TextArticleUI

@Composable
fun TextArticleItem(
    modifier: Modifier = Modifier,
    article: TextArticleUI
) {
    Column(
        modifier = modifier
            .padding(top = 6.dp, bottom = 6.dp)
    ) {
        Text(
            modifier = modifier
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp),
            text = article.title
        )
        Text(
            modifier = modifier
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp),
            text = article.publishedAt
        )
    }
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