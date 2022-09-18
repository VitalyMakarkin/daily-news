package com.example.shared.presentation.model

data class ImageArticleUI(
    override val id: Int,
    val title: String,
    val publishedAt: String,
    val urlToImage: String,
    val isFavorite: Boolean
) : ArticleUI(id)