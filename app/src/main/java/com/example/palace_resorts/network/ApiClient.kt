package com.example.palace_resorts.network

import com.example.palace_resorts.flows.models.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("v2/everything?q=flutter")
    fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("language") language: String
    ): Single<NewsResponse>
}