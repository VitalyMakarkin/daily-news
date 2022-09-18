package com.example.shared.presentation.model

data class TextArticleUI(
    override val id: Int,
    val title: String,
    val publishedAt: String,
    val isFavorite: Boolean
) : ArticleUI(id)