package com.quizapp.model

data class QuestionsApi(
    var id: String,
    var statement: String,
    var options: List<String>
)