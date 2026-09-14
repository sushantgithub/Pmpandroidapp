package com.sushant.pmpstudy.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sushant.pmpstudy.ui.screens.ChapterDetailScreen
import com.sushant.pmpstudy.ui.screens.FormulasScreen
import com.sushant.pmpstudy.ui.screens.LearnScreen
import com.sushant.pmpstudy.ui.screens.QuizHubScreen
import com.sushant.pmpstudy.ui.screens.QuizScreen

private data class Tab(val route: String, val label: String, val icon: ImageVector)

private val tabs = listOf(
    Tab("learn", "Learn", Icons.AutoMirrored.Outlined.MenuBook),
    Tab("formulas", "Formulas", Icons.Outlined.Calculate),
    Tab("quiz", "Quiz", Icons.Outlined.Quiz)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PmpStudyApp(
    darkTheme: Boolean = true,
    onToggleTheme: () -> Unit = {}
) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val destination = backStack?.destination
    val hideBar = destination?.route?.startsWith("chapter") == true ||
        destination?.route?.startsWith("quiz/take") == true

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            if (!hideBar) {
                TopAppBar(
                    title = {},
                    actions = {
                        IconButton(onClick = onToggleTheme) {
                            Icon(
                                imageVector = if (darkTheme) Icons.Outlined.LightMode
                                else Icons.Outlined.DarkMode,
                                contentDescription = if (darkTheme) "Switch to light mode"
                                else "Switch to dark mode",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        },
        bottomBar = {
            if (!hideBar) {
                NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                    tabs.forEach { tab ->
                        val selected = destination?.hierarchy?.any { it.route == tab.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = false
                                    }
                                    launchSingleTop = true
                                    restoreState = false
                                }
                            },
                            icon = { Icon(tab.icon, contentDescription = tab.label) },
                            label = { Text(tab.label) }
                        )
                    }
                }
            }
        }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = "learn",
            modifier = Modifier.padding(inner)
        ) {
            composable("learn") {
                LearnScreen(onOpenChapter = { id -> navController.navigate("chapter/$id") })
            }
            composable(
                route = "chapter/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { entry ->
                val id = entry.arguments?.getString("id").orEmpty()
                ChapterDetailScreen(
                    chapterId = id,
                    onBack = { navController.popBackStack() },
                    onQuiz = { navController.navigate("quiz/take/$id") }
                )
            }
            composable("formulas") { FormulasScreen() }
            composable("quiz") {
                QuizHubScreen(onOpenPack = { id -> navController.navigate("quiz/take/$id") })
            }
            composable(
                route = "quiz/take/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { entry ->
                val id = entry.arguments?.getString("id").orEmpty()
                QuizScreen(packId = id, onBack = { navController.popBackStack() })
            }
        }
    }
}
