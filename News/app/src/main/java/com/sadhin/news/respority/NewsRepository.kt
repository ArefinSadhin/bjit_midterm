package com.sadhin.news.respority

import android.net.LinkAddress
import android.util.Log
import androidx.lifecycle.LiveData
import com.sadhin.news.dao.NewsDao
import com.sadhin.news.model.BookMark
import com.sadhin.news.model.NewsArticle

class NewsRepository (private val newsDao: NewsDao){
//    var list:LiveData<List<NewsArticle>>?=null
    val bookMarkList:LiveData<List<BookMark>> = newsDao.getAllBookmarks()

    fun setList(category: String):List<NewsArticle>{ return newsDao.getAllNews(category) }
    suspend fun categoryCount(category: String):Int{ return  newsDao.categoryCount(category) }
    suspend fun addAllNewsArticles(allNewsArticles:List<NewsArticle>){ newsDao.addAllNews(allNewsArticles) }
    suspend fun deleteAllNewsArticle(){ newsDao.deleteAllNews() }

    suspend fun addBookMark(bookMark: BookMark){ newsDao.addBookmark(bookMark)}
//    fun getAllBookMark():List<BookMark>{ return newsDao.getAllBookmarks() }
    suspend fun deleteBookMark(bookMark: BookMark){ newsDao.deleteBookmark(bookMark) }

}
