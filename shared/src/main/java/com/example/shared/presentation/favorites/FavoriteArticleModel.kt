package com.example.shared.presentation.favorites

data class FavoriteArticleModel(
    val id: Int,
    val source: FavoriteSourceModel,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
) {
    data class FavoriteSourceModel(
        val id: String,
        val name: String
    )
}
