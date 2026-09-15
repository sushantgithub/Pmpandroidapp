package com.sushant.pmpstudy.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class QuizHistoryEntry(
    val packId: String,
    val percent: Int,
    val correct: Int,
    val total: Int,
    val date: String
)

/**
 * In-memory shared state for the session.
 * Resets on app restart (no persistence — add DataStore later for durability).
 */
object AppState {
    // ── Progress tracking ────────────────────────────────────────────────────
    private var readChapterIdsState by mutableStateOf(setOf<String>())
    val readChapterIds: Set<String> get() = readChapterIdsState

    fun markRead(chapterId: String) { readChapterIdsState = readChapterIdsState + chapterId }
    fun markUnread(chapterId: String) { readChapterIdsState = readChapterIdsState - chapterId }
    fun isRead(chapterId: String) = chapterId in readChapterIdsState

    // ── Bookmarks ────────────────────────────────────────────────────────────
    // key = "${chapterId}::$sectionIndex"
    private var bookmarkedKeysState by mutableStateOf(setOf<String>())
    val bookmarkedKeys: Set<String> get() = bookmarkedKeysState

    fun bookmarkKey(chapterId: String, idx: Int) = "$chapterId::$idx"

    fun isBookmarked(chapterId: String, idx: Int) =
        bookmarkKey(chapterId, idx) in bookmarkedKeysState

    fun toggleBookmark(chapterId: String, idx: Int) {
        val k = bookmarkKey(chapterId, idx)
        bookmarkedKeysState = if (k in bookmarkedKeysState) bookmarkedKeysState - k
        else bookmarkedKeysState + k
    }

    // ── Quiz history ─────────────────────────────────────────────────────────
    val quizHistory = mutableStateListOf<QuizHistoryEntry>()

    fun saveQuizResult(packId: String, percent: Int, correct: Int, total: Int) {
        val fmt = SimpleDateFormat("MMM d", Locale.getDefault())
        quizHistory.add(0, QuizHistoryEntry(packId, percent, correct, total, fmt.format(Date())))
        // keep at most 50 entries total
        while (quizHistory.size > 50) quizHistory.removeLastOrNull()
    }

    fun lastResult(packId: String): QuizHistoryEntry? =
        quizHistory.firstOrNull { it.packId == packId }

    fun resultsForPack(packId: String): List<QuizHistoryEntry> =
        quizHistory.filter { it.packId == packId }
}
