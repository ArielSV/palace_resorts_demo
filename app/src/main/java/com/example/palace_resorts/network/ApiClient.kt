package com.example.palace_resorts.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("v2/everything?q=flutter")
    fun getNews(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<Unit>
}