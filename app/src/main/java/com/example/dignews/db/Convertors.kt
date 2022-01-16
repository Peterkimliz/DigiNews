package com.example.dignews.db

import androidx.room.TypeConverter
import com.example.dignews.models.Source

class Convertors {
    @TypeConverter
    fun fromSource(source:Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}