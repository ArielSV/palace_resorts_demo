package com.example.palace_resorts.flows.usecase

import com.example.palace_resorts.flows.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    fun getNews() = repository.getNews()

}