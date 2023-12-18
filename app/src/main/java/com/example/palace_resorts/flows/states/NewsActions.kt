package com.example.palace_resorts.flows.states

import com.example.palace_resorts.flows.models.NewsResponse

sealed interface NewsActions {
    data class OnSuccess(val newsList: NewsResponse) : NewsActions
}