package com.quizapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quizapp.model.QuestionsApi
import com.quizapp.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    val myResponse:MutableLiveData<QuestionsApi> = MutableLiveData()

    fun getQuestionsApi(){
        viewModelScope.launch {
            val response:QuestionsApi = repository.getQuestionsApi()
            myResponse.value=response
        }
    }
}