package com.sadhin.news

import com.sadhin.news.model.Article
import com.sadhin.news.model.BookMark
import com.sadhin.news.model.NewsArticle

class Constant {
    companion object{
        const val BASE_URL="https://newsapi.org"
        const val KEY="1a5af674e4d84d0aaa046b3175bae730"

        const val TOP_NEWS = "top_news"
        const val GENERAL = "general"
        const val BUSINESS = "business"
        const val ENTERTAINMENT = "entertainment"
        const val SPORTS = "sports"
        const val HEALTH = "health"
        const val TECHNOLOGY = "technology"

        fun articleToNewsArticle(
            article: List<Article>, category: String): List<NewsArticle> {
            return article.map { article ->
                NewsArticle(
                    0,
                    article.author,
                    article.content,
                    article.description,
                    article.publishedAt,
                    article.source,
                    article.title,
                    article.url,
                    article.urlToImage,
                    category
                )
            }
        }
        fun newsArticleToBookMark(newsArticle: NewsArticle): BookMark {
            return BookMark(
                0,
                newsArticle.author,
                newsArticle.content,
                newsArticle.description,
                newsArticle.publishedAt,
                newsArticle.source,
                newsArticle.title,
                newsArticle.url,
                newsArticle.urlToImage
            )
        }

    }
}