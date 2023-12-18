package com.example.palace_resorts.flows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palace_resorts.base.viewmodel.BaseViewModel
import com.example.palace_resorts.flows.states.NewsActions
import com.example.palace_resorts.flows.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase,
) : BaseViewModel() {

    private val action = MutableLiveData<NewsActions>()
    fun getAction(): LiveData<NewsActions> = action

    fun getNews() {
        disposable.add(
            newsUseCase.getNews()
                .doOnSubscribe {
                    showProgress.value = true
                }
                .doFinally {
                    showProgress.value = false
                }
                .subscribe(
                    {
                    action.value = NewsActions.OnSuccess(it)
                    },
                    {
                        showMessageText.value = it.message.orEmpty()
                    }
                )
        )
    }
}