package com.sadhin.news.dao

import androidx.room.TypeConverter
import com.sadhin.news.model.Source

class Converters {
    @TypeConverter
    fun sourceToString(source: Source?):String?{
        if(source!=null){ return "${source.id}:${source.name}" }
        return null
    }
    @TypeConverter
    fun stringToSource(source: String?): Source? {
        if (source == null) { return null }
        val parts = source.split(":")
        return Source(parts[0], parts[1])
    }
}