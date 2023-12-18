package com.example.palace_resorts.di

import android.content.Context
import androidx.room.Room
import com.example.palace_resorts.flows.constants.DBConstants
import com.example.palace_resorts.flows.dao.NewsDao
import com.example.palace_resorts.flows.models.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataLocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): NewsDatabase {
        return Room.databaseBuilder(
            appContext,
            NewsDatabase::class.java,
            DBConstants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }
}