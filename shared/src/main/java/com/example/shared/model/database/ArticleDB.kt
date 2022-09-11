package com.example.shared.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleDB(
    @Embedded(prefix = "source_")
    val source: SourceDB,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "cachedAt")
    val cachedAt: String,

    @ColumnInfo(name = "favoritesAt")
    val favoritesAt: String
) {
    data class SourceDB(
        @ColumnInfo(name = "id")
        val id: String,

        @ColumnInfo(name = "name")
        val name: String
    )

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
