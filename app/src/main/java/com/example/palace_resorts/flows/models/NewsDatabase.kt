package com.example.palace_resorts.flows.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.palace_resorts.flows.dao.NewsDao

@Database(
    entities = [EntityNewsItem::class], version = 1, exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}