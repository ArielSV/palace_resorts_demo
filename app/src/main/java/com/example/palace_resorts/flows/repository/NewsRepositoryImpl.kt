package com.example.palace_resorts.flows.repository

import com.example.palace_resorts.flows.dao.NewsDao
import com.example.palace_resorts.network.ApiClient
import com.example.palace_resorts.utils.applySchedulers
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val apiService: ApiClient
) : NewsRepository {

    override fun getNews() = apiService.getNews(API_KEY, LANGUAGE).applySchedulers()

    private companion object {
        const val API_KEY = "7d60904b926a456c818d2a67b6cb67c4"
        const val LANGUAGE = "es"
    }
}