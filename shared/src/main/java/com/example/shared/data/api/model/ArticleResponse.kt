package com.example.shared.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesPageResponse(
    @SerialName("status")
    val status: String,

    @SerialName("totalResults")
    val totalResults: Int,

    @SerialName("articles")
    val articles: List<ArticleResponse>
)

@Serializable
data class ArticleResponse(
    @SerialName("source")
    val source: SourceResponse?,

    @SerialName("author")
    val author: String?,

    @SerialName("title")
    val title: String?,

    @SerialName("description")
    val description: String?,

    @SerialName("url")
    val url: String?,

    @SerialName("urlToImage")
    val urlToImage: String?,

    @SerialName("publishedAt")
    val publishedAt: String?,

    @SerialName("content")
    val content: String?,
) {
    @Serializable
    data class SourceResponse(
        @SerialName("id")
        val id: String?,

        @SerialName("name")
        val name: String?
    )
}
