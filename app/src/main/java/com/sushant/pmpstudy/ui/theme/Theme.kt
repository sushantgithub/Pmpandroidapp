package com.sushant.pmpstudy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Navy = Color(0xFF0F3D5E)
private val Gold = Color(0xFFF4C95D)
private val Ink = Color(0xFF101820)
private val Paper = Color(0xFFF6F1E7)
private val Teal = Color(0xFF2A9D8F)

private val Scheme = darkColorScheme(
    primary = Gold,
    onPrimary = Ink,
    secondary = Teal,
    onSecondary = Color.White,
    background = Color(0xFF0B1C28),
    onBackground = Paper,
    surface = Navy,
    onSurface = Paper,
    surfaceVariant = Color(0xFF17364C),
    onSurfaceVariant = Color(0xFFD7E3EC),
    error = Color(0xFFE07A7A)
)

@Composable
fun PmpStudyTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Scheme, content = content)
}
