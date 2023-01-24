package com.sadhin.news.model

data class News(
    val articles: List<Article>,
    val totalResults: Int?,
    val status: String?

)