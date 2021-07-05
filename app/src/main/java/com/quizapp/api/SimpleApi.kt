package com.quizapp.api

import com.quizapp.model.QuestionsApi
import retrofit2.Call
import retrofit2.http.GET

interface SimpleApi {

    @GET("question")
    suspend fun getQuestionsApi() : QuestionsApi
}