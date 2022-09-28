package com.example.shared.domain.model

data class Article(
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val urlToImage: String,
    val cachedAt: String,
    val favoritesAt: String,
    val isFavorite: Boolean
)
