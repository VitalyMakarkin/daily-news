package com.example.shared.presentation.model

import com.example.shared.domain.model.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private fun datetimeMapper(datetimeString: String): String {
    val servicePattern =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val parsedDateTime = LocalDateTime.parse(datetimeString, servicePattern)
    val localPattern = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy")
    return parsedDateTime.format(localPattern)
}

fun Article.mapToUI(): BaseArticleUI {
    return when {
        this.urlToImage.isNotEmpty() -> ImageArticleUI(
            id = this.id,
            title = this.title,
            publishedAt = datetimeMapper(this.publishedAt),
            urlToImage = this.urlToImage,
            isFavorite = this.isFavorite
        )
        else -> TextArticleUI(
            id = this.id,
            title = this.title,
            description = this.description,
            publishedAt = datetimeMapper(this.publishedAt),
            isFavorite = this.isFavorite
        )
    }
}