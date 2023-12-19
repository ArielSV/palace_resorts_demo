package com.example.palace_resorts.flows.models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.palace_resorts.base.converters.NewsTypeConverters
import com.example.palace_resorts.flows.dao.NewsDao

@Database(
    entities = [EntityNewsItem::class], version = 2, exportSchema = false
)
@TypeConverters(NewsTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}