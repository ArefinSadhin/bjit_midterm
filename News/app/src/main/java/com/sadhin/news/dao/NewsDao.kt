package com.sadhin.news.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sadhin.news.model.BookMark
import com.sadhin.news.model.NewsArticle

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllNews(allNews:List<NewsArticle>)

    @Query("select * from news where category =:id")
    fun getAllNews(id: String): List<NewsArticle>

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()

    @Query ("SELECT COUNT(*) FROM news WHERE category = :category ")
    suspend fun categoryCount(category: String):Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookMark)

    @Query("select * from bookmark")
    fun getAllBookmarks():LiveData<List<BookMark>>

    @Delete
    suspend fun deleteBookmark(bookmark: BookMark)



}