package com.example.shared.presentation.model

import com.example.shared.domain.model.Article

fun Article.mapToUI() = ArticleUI(
    id = this.id,
    author = this.author,
    title = this.title,
    description = this.description,
    publishedAt = this.publishedAt,
    isFavorite = this.isFavorite
)