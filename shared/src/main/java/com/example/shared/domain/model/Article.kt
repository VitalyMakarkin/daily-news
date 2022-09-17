package com.example.shared.domain.model

data class Article(
    val id: Int,
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val favoritesAt: String
) {
    data class Source(
        val id: String,
        val name: String
    )

    val isFavorite: Boolean get() = favoritesAt.isNotEmpty()
}
