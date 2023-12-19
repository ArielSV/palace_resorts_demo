package com.example.palace_resorts.flows.repository

import com.example.palace_resorts.flows.models.Articles
import com.example.palace_resorts.flows.models.EntityNewsItem
import com.example.palace_resorts.flows.models.NewsResponse
import io.reactivex.rxjava3.core.Single

interface NewsRepository {

    fun getNews(): Single<NewsResponse>

    suspend fun saveNews(article: Articles)

    suspend fun deleteNew(id: Int)

    suspend fun getAllNews() : List<EntityNewsItem?>
}