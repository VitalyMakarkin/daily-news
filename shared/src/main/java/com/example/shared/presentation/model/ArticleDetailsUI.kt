package com.example.shared.presentation.model

data class ArticleDetailsUI(
    override val id: Int,
    val title: String,
    val author: String,
    val publishedAt: String,
    val description: String,
    val urlToImage: String,
    val isFavorite: Boolean
) : BaseArticleUI(id)