package com.sushant.pmpstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import com.sushant.pmpstudy.domain.Settings
import com.sushant.pmpstudy.ui.PmpStudyApp
import com.sushant.pmpstudy.ui.theme.DarkBar
import com.sushant.pmpstudy.ui.theme.LightBar
import com.sushant.pmpstudy.ui.theme.PmpStudyTheme
import com.sushant.pmpstudy.ui.theme.isAppInDarkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Load display preferences before the first composition so the app does
        // not flash the default theme before the user's choice is applied.
        Settings.init(applicationContext)
        super.onCreate(savedInstanceState)
        setContent {
            val dark = isAppInDarkTheme()
            // Re-applied whenever the user switches theme, so the status and
            // navigation bar icons stay legible against the new background.
            LaunchedEffect(dark) {
                val bar = (if (dark) DarkBar else LightBar).toArgb()
                enableEdgeToEdge(
                    statusBarStyle = if (dark) SystemBarStyle.dark(bar) else SystemBarStyle.light(bar, bar),
                    navigationBarStyle = if (dark) SystemBarStyle.dark(bar) else SystemBarStyle.light(bar, bar)
                )
            }
            PmpStudyTheme {
                PmpStudyApp()
            }
        }
    }
}
