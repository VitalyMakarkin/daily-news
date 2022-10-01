package com.example.shared.presentation.model

data class TextArticleUI(
    override val id: Int,
    val title: String,
    val description: String,
    val publishedAt: String,
    val isFavorite: Boolean
) : BaseArticleUI(id)