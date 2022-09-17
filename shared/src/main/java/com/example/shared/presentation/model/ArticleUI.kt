package com.example.shared.presentation.model

data class ArticleUI(
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val isFavorite: Boolean
)
