package com.example.palace_resorts.flows.repository

import com.example.palace_resorts.flows.models.NewsResponse
import io.reactivex.rxjava3.core.Single

interface NewsRepository {

    fun getNews(): Single<NewsResponse>
}