package com.example.shared.presentation

data class ArticleModel(
    val id: Int,
    val source: SourceModel,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val favoritesAt: String
) {
    data class SourceModel(
        val id: String,
        val name: String
    )

    val isFavorite: Boolean get() = favoritesAt.isNotEmpty()
}
