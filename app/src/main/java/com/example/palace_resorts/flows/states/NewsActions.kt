package com.example.palace_resorts.flows.states

import com.example.palace_resorts.flows.models.EntityNewsItem
import com.example.palace_resorts.flows.models.NewsResponse

sealed interface NewsActions {
    data object OnDeleteSuccess : NewsActions
    data object OnAddedSuccess : NewsActions
    data class OnSuccess(val newsList: NewsResponse) : NewsActions
    data class GetAllNews(val newsList: List<EntityNewsItem?>) : NewsActions
}