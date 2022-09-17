package com.example.shared.data.api.mapper

import com.example.shared.data.api.model.ArticleResponse
import com.example.shared.data.db.model.ArticleDB
import java.time.LocalDateTime

fun ArticleResponse.SourceResponse.mapToDatabase(): ArticleDB.SourceDB =
    ArticleDB.SourceDB(
        id = this.id ?: "",
        name = this.name ?: ""
    )

fun ArticleResponse.mapToDatabase(): ArticleDB =
    ArticleDB(
        source = this.source?.mapToDatabase() ?: ArticleDB.SourceDB(
            "",
            ""
        ),
        author = this.author ?: "",
        title = this.title ?: "",
        description = this.description ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: "",
        publishedAt = this.publishedAt ?: "",
        content = this.content ?: "",
        cachedAt = LocalDateTime.now().toString(),
        favoritesAt = ""
    )