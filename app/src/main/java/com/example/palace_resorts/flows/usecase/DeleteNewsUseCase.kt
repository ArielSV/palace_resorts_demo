package com.example.palace_resorts.flows.usecase

import com.example.palace_resorts.flows.repository.NewsRepository
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun deleteNew(id: Int) = repository.deleteNew(id)
}