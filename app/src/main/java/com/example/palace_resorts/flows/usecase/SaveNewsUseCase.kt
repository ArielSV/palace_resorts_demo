package com.example.palace_resorts.flows.usecase

import com.example.palace_resorts.flows.models.Articles
import com.example.palace_resorts.flows.repository.NewsRepository
import javax.inject.Inject

class SaveNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun saveNew(articles: Articles) = repository.saveNews(articles)
}