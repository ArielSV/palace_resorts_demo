package com.example.palace_resorts.flows.viewmodel

import androidx.lifecycle.LiveData
import com.example.palace_resorts.base.SingleLiveEvent
import com.example.palace_resorts.base.viewmodel.BaseViewModel
import com.example.palace_resorts.flows.models.Articles
import com.example.palace_resorts.flows.models.EntityNewsItem
import com.example.palace_resorts.flows.states.NewsActions
import com.example.palace_resorts.flows.usecase.DeleteNewsUseCase
import com.example.palace_resorts.flows.usecase.SaveNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val saveNewsUseCase: SaveNewsUseCase,
    private val deleteUseCase: DeleteNewsUseCase
) : BaseViewModel() {

    private val action = SingleLiveEvent<NewsActions>()
    fun getAction(): LiveData<NewsActions> = action

    fun saveNew(article: Articles) {
        GlobalScope.launch {
            saveNewsUseCase.saveNew(article)
            action.postValue(NewsActions.OnAddedSuccess)
        }
    }

    fun deleteNew(article: EntityNewsItem) {
        GlobalScope.launch {
            showProgress.postValue(true)
            deleteUseCase.deleteNew(article.id)
            showProgress.postValue(false)
            action.postValue(NewsActions.OnDeleteSuccess)
        }
    }
}