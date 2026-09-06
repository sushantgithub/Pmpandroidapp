package com.sushant.pmpstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sushant.pmpstudy.ui.PmpStudyApp
import com.sushant.pmpstudy.ui.theme.PmpStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PmpStudyTheme {
                PmpStudyApp()
            }
        }
    }
}
