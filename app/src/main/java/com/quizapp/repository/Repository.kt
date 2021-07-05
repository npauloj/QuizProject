package com.quizapp.repository

import com.quizapp.api.RetrofitInstance
import com.quizapp.model.QuestionsApi

class Repository {

    suspend fun getQuestionsApi():QuestionsApi{
        return RetrofitInstance.api.getQuestionsApi()
    }
}