package com.example.app_compose.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shared.presentation.model.ImageArticleUI

@Composable
fun ImageArticleItem(
    article: ImageArticleUI,
    modifier: Modifier = Modifier,
    // showArticleDetailsAction: (articleId: Int) -> Unit,
    addArticleToFavoritesAction: (articleId: Int) -> Unit,
    removeArticleFromFavoritesAction: (articleId: Int) -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = {})
            .padding(top = 6.dp, bottom = 6.dp)
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            modifier = modifier
                .height(200.dp)
                .padding(top = 6.dp, bottom = 6.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = article.title,
            modifier = modifier
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = article.publishedAt,
                modifier = modifier
                    .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
            )
            if (article.isFavorite)
                Image(
                    painter = painterResource(
                        id = com.example.shared.R.drawable.ic_favorite_marked
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
                        .height(32.dp)
                        .width(32.dp)
                        .clickable(onClick = { removeArticleFromFavoritesAction(article.id) })
                )
            else
                Image(
                    painter = painterResource(
                        id = com.example.shared.R.drawable.ic_favorite_unmarked
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
                        .height(32.dp)
                        .width(32.dp)
                        .clickable(onClick = { addArticleToFavoritesAction(article.id) })
                )
        }
    }
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
        ),
        addArticleToFavoritesAction = {},
        removeArticleFromFavoritesAction = {}
    )
}