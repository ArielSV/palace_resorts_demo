package com.example.palace_resorts.flows.usecase

import com.example.palace_resorts.flows.repository.NewsRepository
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun getAllNews() = repository.getAllNews()

}