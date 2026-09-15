package com.sushant.pmpstudy.domain

import com.sushant.pmpstudy.data.QuizQuestion

data class QuizResult(
    val total: Int,
    val correct: Int
) {
    val percent: Int get() = if (total == 0) 0 else (correct * 100) / total
}

object QuizGrader {
    fun grade(questions: List<QuizQuestion>, answers: Map<String, Int>): QuizResult {
        val correct = questions.count { question ->
            answers[question.id] == question.correctIndex
        }
        return QuizResult(total = questions.size, correct = correct)
    }
}
