package com.sadhin.news.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadhin.news.dao.NewsDatabase
import com.sadhin.news.model.BookMark
import com.sadhin.news.model.NewsArticle
import com.sadhin.news.respority.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookMarkViewModel(application: Application): AndroidViewModel(application) {
    val list:LiveData<List<BookMark>>
    private val repository: NewsRepository
    init {
        val newsDao=NewsDatabase.getDatabase(application).NewsDao()
        repository = NewsRepository(newsDao)
        list=repository.bookMarkList
    }


}