package com.sushant.pmpstudy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Navy = Color(0xFF0B1524)
val Surface = Color(0xFF122033)
val SurfaceRaised = Color(0xFF1A2D44)
val Gold = Color(0xFFE8C547)
val Teal = Color(0xFF3DDC97)
val Ink = Color(0xFF0B1524)
val Paper = Color(0xFFF4F7FB)
val Muted = Color(0xFFA8B8C8)

val CalloutNote = Color(0xFF1B3A63)
val CalloutTip = Color(0xFF16463A)
val CalloutWarn = Color(0xFF5C3B12)
val CalloutDanger = Color(0xFF5C1E24)
val CalloutKey = Color(0xFF3B2A6B)

val LightBg = Color(0xFFF5F7FA)
val LightSurface = Color(0xFFFFFFFF)
val LightSurfaceRaised = Color(0xFFEEF2F8)
val LightGold = Color(0xFFB8860B)
val LightTeal = Color(0xFF1A7A5E)
val LightInk = Color(0xFF0C1A2E)
val LightMuted = Color(0xFF6B7E96)

val LCalloutNote = Color(0xFFDBEAFF)
val LCalloutTip = Color(0xFFD1FAE5)
val LCalloutWarn = Color(0xFFFEF3C7)
val LCalloutDanger = Color(0xFFFFE4E6)
val LCalloutKey = Color(0xFFEDE9FE)

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
    outline = Color(0xFF2E4660)
)

private val LightScheme = lightColorScheme(
    primary = LightGold,
    onPrimary = Color.White,
    secondary = LightTeal,
    onSecondary = Color.White,
    background = LightBg,
    onBackground = LightInk,
    surface = LightSurface,
    onSurface = LightInk,
    surfaceVariant = LightSurfaceRaised,
    onSurfaceVariant = LightMuted,
    error = Color(0xFFB00020),
    outline = Color(0xFFCDD5DF)
)

private val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 23.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 19.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        letterSpacing = 0.4.sp
    )
)

data class CalloutColors(
    val note: Color,
    val tip: Color,
    val warn: Color,
    val danger: Color,
    val key: Color
)

@Composable
fun calloutColors(dark: Boolean = isSystemInDarkTheme()) = if (dark) {
    CalloutColors(CalloutNote, CalloutTip, CalloutWarn, CalloutDanger, CalloutKey)
} else {
    CalloutColors(LCalloutNote, LCalloutTip, LCalloutWarn, LCalloutDanger, LCalloutKey)
}

@Composable
fun PmpStudyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkScheme else LightScheme,
        typography = AppTypography,
        content = content
    )
}
