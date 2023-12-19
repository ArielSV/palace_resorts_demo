package com.example.palace_resorts.flows.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.palace_resorts.flows.models.EntityNewsItem

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: EntityNewsItem)

    @Query("DELETE FROM news WHERE id = :newID")
    fun deleteItem(newID: Int)

    @Query("select * from news")
    fun getAllNews(): List<EntityNewsItem?>
}