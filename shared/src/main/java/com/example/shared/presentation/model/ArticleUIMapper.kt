package com.example.shared.presentation.model

import com.example.shared.domain.model.Article

fun Article.mapToUI(): ArticleUI {
    return when {
        this.urlToImage.isNotEmpty() -> ImageArticleUI(
            id = this.id,
            title = this.title,
            publishedAt = this.publishedAt,
            urlToImage = this.urlToImage,
            isFavorite = this.isFavorite
        )
        else -> TextArticleUI(
            id = this.id,
            title = this.title,
            publishedAt = this.publishedAt,
            isFavorite = this.isFavorite
        )
    }
}