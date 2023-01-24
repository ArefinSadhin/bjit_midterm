package com.sadhin.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "bookmark")
class BookMark (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?
){}