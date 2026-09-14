package com.sushant.pmpstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.sushant.pmpstudy.ui.PmpStudyApp
import com.sushant.pmpstudy.ui.theme.PmpStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme = isSystemInDarkTheme()
            val darkBar = Color(0xFF0B1524).toArgb()
            val lightBar = Color(0xFFF5F7FA).toArgb()
            SideEffect {
                enableEdgeToEdge(
                    statusBarStyle = if (darkTheme) SystemBarStyle.dark(darkBar)
                    else SystemBarStyle.light(lightBar, darkBar),
                    navigationBarStyle = if (darkTheme) SystemBarStyle.dark(darkBar)
                    else SystemBarStyle.light(lightBar, darkBar)
                )
            }
            PmpStudyTheme(darkTheme = darkTheme) {
                PmpStudyApp()
            }
        }
    }
}
