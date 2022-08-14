package com.example.dailynews.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleModel(
    @ColumnInfo(name = "source")
    @Embedded
    val source: SourceModel,

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
) {
    data class SourceModel(
        @ColumnInfo(name = "id")
        val id: String,

        @ColumnInfo(name = "name")
        val name: String
    )

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
