package com.sushant.pmpstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.sushant.pmpstudy.ui.PmpStudyApp
import com.sushant.pmpstudy.ui.theme.PmpStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        val bar = Color(0xFF0B1524).toArgb()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(bar),
            navigationBarStyle = SystemBarStyle.dark(bar)
        )
        setContent {
            PmpStudyTheme {
                PmpStudyApp()
            }
        }
    }
}
