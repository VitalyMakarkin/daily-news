package com.example.shared.presentation.model

data class ArticleUI(
    val id: Int,
    val source: SourceUI,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val favoritesAt: String
) {
    data class SourceUI(
        val id: String,
        val name: String
    )

    val isFavorite: Boolean get() = favoritesAt.isNotEmpty()
}
