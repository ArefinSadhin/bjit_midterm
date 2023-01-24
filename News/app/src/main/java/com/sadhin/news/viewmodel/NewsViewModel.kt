package com.sadhin.news.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadhin.news.Constant
import com.sadhin.news.NewsAdapter
import com.sadhin.news.dao.NewsDatabase
import com.sadhin.news.model.BookMark
import com.sadhin.news.model.News
import com.sadhin.news.model.NewsArticle
import com.sadhin.news.network.NewsApi
import com.sadhin.news.respority.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(application: Application): AndroidViewModel(application) {
     private val temp = MutableLiveData<List<NewsArticle>>()
    var list:LiveData<List<NewsArticle>> =temp
//    lateinit var list:LiveData<List<NewsArticle>>
    private val repository: NewsRepository
    init {
        repository = NewsRepository(NewsDatabase.getDatabase(application).NewsDao())
        deleteAllArticle()
        initialLoad()
    }

    fun setList(category: String){
//        Log.d("TAG:viewmodel", "setList: Called()")
        viewModelScope.launch (Dispatchers.IO){
            temp.postValue(repository.setList(category))
//            repository.setList(category)
//            list= repository.list

        }

    }

    private fun initialLoad() {
        loadTopNews()
        loadNewsByCategory(Constant.GENERAL)
        loadNewsByCategory(Constant.BUSINESS)
        loadNewsByCategory(Constant.ENTERTAINMENT)
        loadNewsByCategory(Constant.SPORTS)
    }

    private fun loadNewsByCategory(category:String){
        viewModelScope.launch {
            val newsArticle =Constant.articleToNewsArticle(NewsApi.retrofitService.getNewsByCategory(category).articles,category)
            repository.addAllNewsArticles(newsArticle)
        }
    }
    private fun loadTopNews(){
        viewModelScope.launch {
            val newsArticle =Constant.articleToNewsArticle(NewsApi.retrofitService.getTopNews().articles,Constant.TOP_NEWS)
            repository.addAllNewsArticles(newsArticle)
            temp.postValue(newsArticle)
        }
    }

    private fun deleteAllArticle(){
        viewModelScope.launch(Dispatchers.IO) {repository.deleteAllNewsArticle()  }
    }
    fun addBookMark(news:NewsArticle){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.addBookMark(Constant.newsArticleToBookMark(news))
        }
    }
}