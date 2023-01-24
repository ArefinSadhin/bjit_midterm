package com.sadhin.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sadhin.news.dao.Converters

@Entity(tableName = "news")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @TypeConverters(Converters::class)
    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val category:String?,
)