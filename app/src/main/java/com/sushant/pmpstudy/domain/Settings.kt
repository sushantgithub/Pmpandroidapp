package com.sushant.pmpstudy.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class ThemeMode(val label: String) {
    SYSTEM("System"),
    LIGHT("Light"),
    DARK("Dark")
}

enum class FontScale(val scale: Float, val label: String) {
    SMALL(0.87f, "Small"),
    MEDIUM(1.0f, "Default"),
    LARGE(1.15f, "Large"),
    XLARGE(1.32f, "Largest")
}

/**
 * Display preferences, persisted in SharedPreferences.
 *
 * Backed by Compose state so every screen recomposes when a preference changes.
 * [init] should run before the first composition (see MainActivity). If it has
 * not, reads still return the defaults and writes are simply not persisted.
 */
object Settings {
    private const val PREFS = "pmp_study_settings"
    private const val KEY_THEME = "theme_mode"
    private const val KEY_FONT = "font_scale"

    private var prefs: SharedPreferences? = null

    // Backing state is separate from the public property so the setter can
    // persist as a side effect. A `var` with an explicit setThemeMode() function
    // instead would clash: both compile to the JVM signature setThemeMode(..).
    private var themeModeState by mutableStateOf(ThemeMode.SYSTEM)
    private var fontScaleState by mutableStateOf(FontScale.MEDIUM)

    var themeMode: ThemeMode
        get() = themeModeState
        set(value) {
            themeModeState = value
            prefs?.edit()?.putString(KEY_THEME, value.name)?.apply()
        }

    var fontScale: FontScale
        get() = fontScaleState
        set(value) {
            fontScaleState = value
            prefs?.edit()?.putString(KEY_FONT, value.name)?.apply()
        }

    fun init(context: Context) {
        val stored = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs = stored
        // Assign the backing state directly: going through the setters would
        // immediately write back the values we just read.
        themeModeState = stored.getString(KEY_THEME, null).toThemeMode()
        fontScaleState = stored.getString(KEY_FONT, null).toFontScale()
    }

    // Unknown/absent values fall back to the default rather than throwing, so a
    // renamed enum constant in a future version cannot crash on first launch.
    private fun String?.toThemeMode(): ThemeMode =
        this?.let { name -> runCatching { ThemeMode.valueOf(name) }.getOrNull() } ?: ThemeMode.SYSTEM

    private fun String?.toFontScale(): FontScale =
        this?.let { name -> runCatching { FontScale.valueOf(name) }.getOrNull() } ?: FontScale.MEDIUM
}
