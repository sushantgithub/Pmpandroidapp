package com.sushant.pmpstudy.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt

/**
 * Per-quiz-pack results, persisted so progress survives closing the app.
 *
 * Backed by Compose state so the quiz hub and chapter list update the moment an
 * attempt is recorded. [init] runs before the first composition (see
 * MainActivity); without it reads return empty and writes are simply dropped.
 */
object AppState {

    data class PackProgress(
        val bestPercent: Int,
        val lastPercent: Int,
        val attempts: Int,
        val lastAttemptAt: Long
    )

    private const val PREFS = "pmp_study_progress"

    private var prefs: SharedPreferences? = null
    private var records by mutableStateOf<Map<String, PackProgress>>(emptyMap())

    /** How many distinct packs have been attempted at least once. */
    val attemptedPacks: Int get() = records.size

    /** Mean of every attempted pack's best score, or null when nothing is attempted. */
    val averageBest: Int?
        get() = records.values
            .takeIf { it.isNotEmpty() }
            ?.map { it.bestPercent }
            ?.average()
            ?.roundToInt()

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
        prefs?.edit()?.putString(packId, updated.encode())?.apply()
        return isBest
    }

    fun reset() {
        records = emptyMap()
        prefs?.edit()?.clear()?.apply()
    }

    fun init(context: Context) {
        val stored = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs = stored
        records = stored.all
            .mapNotNull { (packId, raw) -> (raw as? String)?.decode()?.let { packId to it } }
            .toMap()
    }

    private fun PackProgress.encode() =
        "$bestPercent|$lastPercent|$attempts|$lastAttemptAt"

    // A record written by a different build that no longer parses is dropped rather
    // than taking the launch down with it.
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
