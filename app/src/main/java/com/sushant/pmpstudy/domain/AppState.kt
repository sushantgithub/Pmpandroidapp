package com.sushant.pmpstudy.domain

/**
 * Lightweight in-memory store for quiz outcomes.
 *
 * QuizScreen records each completed attempt here so a future history/progress
 * screen can surface best scores and trends. State lives for the lifetime of the
 * process; swap the backing list for DataStore/Room when durable persistence
 * across process death is required.
 */
object AppState {

    data class QuizAttempt(
        val packId: String,
        val percent: Int,
        val correct: Int,
        val total: Int,
        val timestamp: Long = System.currentTimeMillis()
    )

    private val attempts = mutableListOf<QuizAttempt>()

    /** All recorded attempts, oldest first. */
    val quizHistory: List<QuizAttempt> get() = attempts.toList()

    fun saveQuizResult(packId: String, percent: Int, correct: Int, total: Int) {
        attempts.add(QuizAttempt(packId, percent, correct, total))
    }

    /** Highest percentage scored on a given pack, or null if never attempted. */
    fun bestPercent(packId: String): Int? =
        attempts.filter { it.packId == packId }.maxOfOrNull { it.percent }

    /** Most recent attempt for a given pack, or null if never attempted. */
    fun lastAttempt(packId: String): QuizAttempt? =
        attempts.lastOrNull { it.packId == packId }
}
