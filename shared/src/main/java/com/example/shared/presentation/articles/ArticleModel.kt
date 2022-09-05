package com.example.shared.presentation.articles

data class ArticleModel(
    val id: Int,
    val source: SourceModel,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
) {
    data class SourceModel(
        val id: String,
        val name: String
    )
}
