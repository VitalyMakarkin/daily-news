package com.example.dailynews.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesPage(
    @SerialName("status")
    val status: String,

    @SerialName("totalResults")
    val totalResults: Int,

    @SerialName("articles")
    val articles: List<Article>
)

@Serializable
data class Source(
    @SerialName("id")
    val id: String?,

    @SerialName("name")
    val name: String?
)

@Serializable
data class Article(
    @SerialName("source")
    val source: Source?,

    @SerialName("author")
    val author: String?,

    @SerialName("title")
    val title: String?,

    @SerialName("description")
    val description: String?,

    // TODO("Convert field type from String to Uri")
    @SerialName("url")
    val url: String?,

    // TODO("Convert field type from String to Uri")
    @SerialName("urlToImage")
    val urlToImage: String?,

    // TODO("Convert field type from String to Date")
    @SerialName("publishedAt")
    val publishedAt: String?,

    @SerialName("content")
    val content: String?,
)
