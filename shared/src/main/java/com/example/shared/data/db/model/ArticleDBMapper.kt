package com.example.shared.data.db.mapper

import com.example.shared.data.db.model.ArticleDB
import com.example.shared.domain.model.Article

fun ArticleDB.mapToDomain(): Article = Article(
    id = this.id,
    author = this.author,
    title = this.title,
    description = this.description,
    publishedAt = this.publishedAt,
    isFavorite = this.favoritesAt.isNotEmpty()
)