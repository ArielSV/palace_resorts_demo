package com.example.palace_resorts.flows.models

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    val articlesList: List<Articles>
)

data class Articles(
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
)