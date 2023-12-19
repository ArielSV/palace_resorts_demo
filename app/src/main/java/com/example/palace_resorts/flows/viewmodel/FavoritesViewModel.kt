package com.example.palace_resorts.flows.viewmodel

import androidx.lifecycle.LiveData
import com.example.palace_resorts.base.SingleLiveEvent
import com.example.palace_resorts.base.viewmodel.BaseViewModel
import com.example.palace_resorts.flows.states.NewsActions
import com.example.palace_resorts.flows.usecase.GetAllNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val useCase: GetAllNewsUseCase
) : BaseViewModel() {

    private val action = SingleLiveEvent<NewsActions>()
    fun getAction(): LiveData<NewsActions> = action

    fun getAllNews() {
        GlobalScope.launch {
            showProgress.postValue(true)
            val news =  useCase.getAllNews()
            news.let {
                if (news.isNotEmpty()) {
                    showProgress.postValue(false)
                    action.postValue(NewsActions.GetAllNews(it))
                }
            }
        }
    }
}