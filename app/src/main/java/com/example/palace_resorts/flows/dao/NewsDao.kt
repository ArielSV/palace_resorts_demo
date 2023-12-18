package com.example.palace_resorts.flows.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.palace_resorts.flows.constants.DBConstants.NEWS_TABLE
import com.example.palace_resorts.flows.models.EntityNewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNew(news: EntityNewsItem?)

    @Query("select * from $NEWS_TABLE")
    fun getAllNews(): Flow<List<EntityNewsItem?>?>
}