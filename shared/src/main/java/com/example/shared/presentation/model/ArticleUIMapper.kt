package com.example.shared.presentation.model

import com.example.shared.domain.model.Article

fun ArticleUI.mapFromDomain(article: Article) = ArticleUI(
    id = article.id,
    author = article.author,
    title = article.title,
    description = article.description,
    publishedAt = article.publishedAt,
    isFavorite = article.isFavorite
)