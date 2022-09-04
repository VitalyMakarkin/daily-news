package com.example.shared.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_table")
data class ArticleDB(
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
        @ColumnInfo(name = "source_id")
        val id: String,

        @ColumnInfo(name = "source_name")
        val name: String
    )

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
