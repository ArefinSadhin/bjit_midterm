package com.sadhin.news.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sadhin.news.model.BookMark
import com.sadhin.news.model.NewsArticle

@TypeConverters(Converters::class)
@Database(entities = [NewsArticle::class,BookMark::class], version = 12, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun NewsDao():NewsDao
    companion object{
        @Volatile
        private var INSTANCE:NewsDatabase? = null
        fun getDatabase(context: Context):NewsDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE!!
            }
        }
    }
}