package com.example.shared.data.db.model

import com.example.shared.domain.model.Article

fun ArticleDB.mapToDomain(): Article = Article(
    id = this.id,
    author = this.author,
    title = this.title,
    description = this.description,
    publishedAt = this.publishedAt,
    urlToImage = this.urlToImage,
    url = this.url,
    cachedAt = this.cachedAt,
    favoritesAt = this.favoritesAt,
    isFavorite = this.favoritesAt.isNotEmpty()
)