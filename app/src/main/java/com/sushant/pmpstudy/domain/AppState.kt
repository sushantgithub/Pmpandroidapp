package com.sushant.pmpstudy.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt

/**
 * Persistent app progress shared across Compose screens.
 *
 * Quiz summaries, reading progress and bookmarks live in one private
 * SharedPreferences file. All public collections are backed by Compose state so
 * the UI refreshes immediately when a value changes.
 */
object AppState {

    data class PackProgress(
        val bestPercent: Int,
        val lastPercent: Int,
        val attempts: Int,
        val lastAttemptAt: Long
    )

    private const val PREFS = "pmp_study_progress"
    private const val QUIZ_PREFIX = "quiz::"
    private const val KEY_READ_CHAPTERS = "__read_chapters"
    private const val KEY_BOOKMARKS = "__bookmarks"

    private var prefs: SharedPreferences? = null

    private var records by mutableStateOf<Map<String, PackProgress>>(emptyMap())
    private var readChapterIdsState by mutableStateOf<Set<String>>(emptySet())
    private var bookmarkedKeysState by mutableStateOf<Set<String>>(emptySet())

    /** How many distinct packs have been attempted at least once. */
    val attemptedPacks: Int get() = records.size

    /** Mean of every attempted pack's best score, or null when nothing is attempted. */
    val averageBest: Int?
        get() = records.values
            .takeIf { it.isNotEmpty() }
            ?.map { it.bestPercent }
            ?.average()
            ?.roundToInt()

    val readChapterIds: Set<String> get() = readChapterIdsState
    val bookmarkedKeys: Set<String> get() = bookmarkedKeysState

    fun progressFor(packId: String): PackProgress? = records[packId]

    /** Records one finished attempt and returns true when it beat the previous best. */
    fun saveQuizResult(packId: String, percent: Int): Boolean {
        val previous = records[packId]
        val isBest = percent > (previous?.bestPercent ?: -1)
        val updated = PackProgress(
            bestPercent = maxOf(percent, previous?.bestPercent ?: 0),
            lastPercent = percent,
            attempts = (previous?.attempts ?: 0) + 1,
            lastAttemptAt = System.currentTimeMillis()
        )
        records = records + (packId to updated)

        // Remove the legacy un-prefixed key if one exists, then persist in the
        // namespaced format so quiz IDs can never collide with other app state.
        prefs?.edit()
            ?.remove(packId)
            ?.putString(quizKey(packId), updated.encode())
            ?.apply()

        return isBest
    }

    /**
     * Clears quiz scores only. Reading progress and bookmarks are intentionally
     * preserved because Settings labels this action "Reset progress" for quizzes.
     */
    fun reset() {
        records = emptyMap()
        prefs?.let { stored ->
            val editor = stored.edit()
            stored.all.forEach { (key, raw) ->
                if (raw is String && key != KEY_READ_CHAPTERS && key != KEY_BOOKMARKS) {
                    editor.remove(key)
                }
            }
            editor.apply()
        }
    }

    fun markRead(chapterId: String) {
        if (chapterId in readChapterIdsState) return
        readChapterIdsState = readChapterIdsState + chapterId
        persistSet(KEY_READ_CHAPTERS, readChapterIdsState)
    }

    fun markUnread(chapterId: String) {
        if (chapterId !in readChapterIdsState) return
        readChapterIdsState = readChapterIdsState - chapterId
        persistSet(KEY_READ_CHAPTERS, readChapterIdsState)
    }

    fun isRead(chapterId: String): Boolean = chapterId in readChapterIdsState

    fun bookmarkKey(chapterId: String, sectionIndex: Int): String =
        "$chapterId::$sectionIndex"

    fun isBookmarked(chapterId: String, sectionIndex: Int): Boolean =
        bookmarkKey(chapterId, sectionIndex) in bookmarkedKeysState

    fun toggleBookmark(chapterId: String, sectionIndex: Int) {
        val key = bookmarkKey(chapterId, sectionIndex)
        bookmarkedKeysState =
            if (key in bookmarkedKeysState) bookmarkedKeysState - key
            else bookmarkedKeysState + key
        persistSet(KEY_BOOKMARKS, bookmarkedKeysState)
    }

    fun init(context: Context) {
        val stored = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs = stored

        // Support both the old un-prefixed quiz keys and the new namespaced keys.
        // If both exist, retain whichever record was written most recently.
        val loaded = mutableMapOf<String, PackProgress>()
        stored.all.forEach { (key, raw) ->
            val progress = (raw as? String)?.decode() ?: return@forEach
            val packId = if (key.startsWith(QUIZ_PREFIX)) key.removePrefix(QUIZ_PREFIX) else key
            val current = loaded[packId]
            if (current == null || progress.lastAttemptAt >= current.lastAttemptAt) {
                loaded[packId] = progress
            }
        }
        records = loaded

        readChapterIdsState = stored.getStringSet(KEY_READ_CHAPTERS, emptySet())
            ?.toSet()
            .orEmpty()
        bookmarkedKeysState = stored.getStringSet(KEY_BOOKMARKS, emptySet())
            ?.toSet()
            .orEmpty()
    }

    private fun persistSet(key: String, value: Set<String>) {
        // SharedPreferences may retain the supplied mutable set by reference on
        // some implementations; always give it a fresh copy.
        prefs?.edit()?.putStringSet(key, value.toSet())?.apply()
    }

    private fun quizKey(packId: String) = QUIZ_PREFIX + packId

    private fun PackProgress.encode() =
        "$bestPercent|$lastPercent|$attempts|$lastAttemptAt"

    // A record written by a different build that no longer parses is dropped
    // rather than taking app launch down with it.
    private fun String.decode(): PackProgress? {
        val f = split('|')
        if (f.size != 4) return null
        return PackProgress(
            bestPercent = f[0].toIntOrNull() ?: return null,
            lastPercent = f[1].toIntOrNull() ?: return null,
            attempts = f[2].toIntOrNull() ?: return null,
            lastAttemptAt = f[3].toLongOrNull() ?: return null
        )
    }
}
