package com.sadhin.news.network

import com.sadhin.news.Constant.Companion.BASE_URL
import com.sadhin.news.Constant.Companion.KEY
import com.sadhin.news.model.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface NewsApiService{
    @GET("/v2/top-headlines?country=us&apiKey=$KEY")
    suspend fun getTopNews(): News
    @GET("/v2/top-headlines")
    suspend fun getNewsByCategory(@Query("category") category: String, @Query("apiKey") apiKey: String = KEY): News

}

object NewsApi {
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}