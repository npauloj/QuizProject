package com.quizapp.utils

import com.quizapp.R
import com.quizapp.Question

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"


    fun getQuestionsReserva(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1, "Quais os nomes dos três Reis Magos?",
            "Belchior, Gaspar e Baltazar", "Belchior, Gaspar e Nataniel",
            "Gaspar, Nicolau e Natanael", "Gabriel, Benjamim e Melchior","Opcao5", 1
        )

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "Em que período da pré-história o fogo foi descoberto?",
            "Idade Média", "Paleolítico",
            "Idade dos Metais", "Neolítico","Opcao5", 2
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "Quais os principais heterônimos de Fernando Pessoa?",
            "Ariano Suassuna, Raul Bopp e Quincas Borba", "Bento Teixeira, Ricardo Reis e Haroldo de Campos",
            "Alberto Caeiro, Ricardo Reis e Álvaro de Campos", "Alberto Caeiro, Ricardo Leite e Augusto de Campos","Opcao5", 3
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "Qual das alternativas abaixo apenas contêm classes de palavras?",
            "Hiatos, ditongos e tritongos", "Artigo, verbo transitivo e verbo intransitivo",
            "Vogais, semivogais e consoantes", "Substantivo, verbo e preposição","Opcao5", 4
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "Em qual local da Ásia o português é língua oficial?",
            "Índia","Macau",
            "Filipinas","Moçambique","Opcao5", 2
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "Qual a montanha mais alta do Brasil?",
            "Monte Roraima", "Pico da Bandeira",
            "Pico Paraná", "Pico da Neblina","Opcao5", 4
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "Qual desses filmes foi baseado na obra de Shakespeare?",
            "Capitães de Areia (2011)", "A Dama das Camélias (1936)",
            "Muito Barulho por Nada (2012)", "A Revolução dos Bichos (1954)","Opcao5", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "Qual a velocidade da luz?",
            "199 792 458 metros por segundo (m/s)", "150 000 000 metros por segundo (m/s)",
            "299 792 458 metros por segundo (m/s)", "30 000 000 metros por segundo (m/s)","Opcao5", 3
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "What country does this flag belong to?",
            "Australia", "New Zealand",
            "Tuvalu", "United States of America","Opcao5", 2
        )

        questionsList.add(que9)

        // 10
        // essa pergunta tem bug
        val que10 = Question(
            10, "Quem foi o primeiro homem a pisar na Lua? Em que ano aconteceu?",
            "Neils Armstrong, em 1969", "Buzz Aldrin, em 1969",
            "Charles Duke, em 1971", "Yuri Gagarin, em 1961","Opcao5", 1
        )

        questionsList.add(que10)

        return questionsList
    }
}