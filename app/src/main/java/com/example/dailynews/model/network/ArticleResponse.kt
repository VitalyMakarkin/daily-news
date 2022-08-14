package com.example.dailynews.model.network

import com.example.dailynews.model.database.ArticleModel
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
) {
    @Serializable
    data class SourceResponse(
        @SerialName("id")
        val id: String?,

        @SerialName("name")
        val name: String?
    )
}

fun ArticleResponse.SourceResponse.mapToDatabase(): ArticleModel.SourceModel =
    ArticleModel.SourceModel(
        id = this.id ?: "",
        name = this.name ?: ""
    )

fun ArticleResponse.mapToDatabase(): ArticleModel =
    ArticleModel(
        source = this.source?.mapToDatabase() ?: ArticleModel.SourceModel(
            "",
            ""
        ),
        author = this.author ?: "",
        title = this.title ?: "",
        description = this.description ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: "",
        publishedAt = this.publishedAt ?: "",
        content = this.content ?: ""
    )
