package com.sushant.pmpstudy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sushant.pmpstudy.domain.Settings
import com.sushant.pmpstudy.domain.ThemeMode

val Navy = Color(0xFF0B1524)
val Surface = Color(0xFF122033)
val SurfaceRaised = Color(0xFF1A2D44)
val Gold = Color(0xFFE8C547)
val Teal = Color(0xFF3DDC97)
val Ink = Color(0xFF0B1524)
val Paper = Color(0xFFF4F7FB)
val Muted = Color(0xFFA8B8C8)

/** Background the window/system bars use per theme. */
val DarkBar = Navy
val LightBar = Color(0xFFF7F9FC)

private val DarkScheme = darkColorScheme(
    primary = Gold,
    onPrimary = Ink,
    secondary = Teal,
    onSecondary = Ink,
    background = Navy,
    onBackground = Paper,
    surface = Surface,
    onSurface = Paper,
    surfaceVariant = SurfaceRaised,
    onSurfaceVariant = Muted,
    error = Color(0xFFFF8A8A),
    onError = Ink,
    outline = Color(0xFF2E4660)
)

// Gold and teal are darkened for light mode: the dark-theme values are far too
// pale to read as text or to carry white button labels on a white surface.
private val LightScheme = lightColorScheme(
    primary = Color(0xFF8A6B00),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF0E7A55),
    onSecondary = Color(0xFFFFFFFF),
    background = LightBar,
    onBackground = Color(0xFF0B1524),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0B1524),
    surfaceVariant = Color(0xFFE9EEF5),
    onSurfaceVariant = Color(0xFF4A5A6B),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    outline = Color(0xFFC2CDDA)
)

/** Background / text / accent-bar colours for one callout kind. */
@Immutable
data class CalloutStyle(val bg: Color, val fg: Color, val accent: Color)

@Immutable
data class CalloutPalette(
    val note: CalloutStyle,
    val tip: CalloutStyle,
    val warn: CalloutStyle,
    val danger: CalloutStyle,
    val key: CalloutStyle
)

private val DarkCallouts = CalloutPalette(
    note = CalloutStyle(Color(0xFF1B3A63), Color(0xFFD6E4FF), Color(0xFF7EB6FF)),
    tip = CalloutStyle(Color(0xFF16463A), Color(0xFFD1FAE5), Color(0xFF3DDC97)),
    warn = CalloutStyle(Color(0xFF5C3B12), Color(0xFFFEF3C7), Color(0xFFE8C547)),
    danger = CalloutStyle(Color(0xFF5C1E24), Color(0xFFFECACA), Color(0xFFFF8A8A)),
    key = CalloutStyle(Color(0xFF3B2A6B), Color(0xFFEDE9FE), Color(0xFFC4B5FD))
)

// Light mode inverts each callout: pale tinted background, dark text.
private val LightCallouts = CalloutPalette(
    note = CalloutStyle(Color(0xFFDCE9FF), Color(0xFF10294D), Color(0xFF2D6CDF)),
    tip = CalloutStyle(Color(0xFFD7F5E6), Color(0xFF0B3A29), Color(0xFF0E9C6B)),
    warn = CalloutStyle(Color(0xFFFDF0CE), Color(0xFF4A3306), Color(0xFFB07D0A)),
    danger = CalloutStyle(Color(0xFFFEE1E1), Color(0xFF551419), Color(0xFFC8323E)),
    key = CalloutStyle(Color(0xFFEAE4FD), Color(0xFF2B2050), Color(0xFF6D4FD0))
)

val LocalCalloutPalette = staticCompositionLocalOf { DarkCallouts }

/** Type ramp scaled by the user's text-size preference. */
fun appTypography(scale: Float): Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp * scale,
        lineHeight = 36.sp * scale
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp * scale,
        lineHeight = 32.sp * scale
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp * scale,
        lineHeight = 26.sp * scale
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp * scale,
        lineHeight = 23.sp * scale
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp * scale,
        lineHeight = 20.sp * scale
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp * scale,
        lineHeight = 24.sp * scale
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp * scale,
        lineHeight = 22.sp * scale
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp * scale,
        lineHeight = 19.sp * scale
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp * scale
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp * scale,
        letterSpacing = 0.4.sp
    )
)

/** Whether the app should render dark right now, honouring the user's preference. */
@Composable
fun isAppInDarkTheme(): Boolean = when (Settings.themeMode) {
    ThemeMode.SYSTEM -> isSystemInDarkTheme()
    ThemeMode.LIGHT -> false
    ThemeMode.DARK -> true
}

@Composable
fun PmpStudyTheme(content: @Composable () -> Unit) {
    val dark = isAppInDarkTheme()
    MaterialTheme(
        colorScheme = if (dark) DarkScheme else LightScheme,
        typography = appTypography(Settings.fontScale.scale)
    ) {
        CompositionLocalProvider(
            LocalCalloutPalette provides if (dark) DarkCallouts else LightCallouts,
            content = content
        )
    }
}
