package org.example.gems

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.example.gems.navigation.LocalNavigationController
import org.example.gems.navigation.NavBackHandler
import org.example.gems.navigation.NavDirection
import org.example.gems.navigation.Screen
import org.example.gems.navigation.rememberNavigationController
import org.example.gems.ui.screens.*
import org.example.gems.ui.theme.GEMSTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavigationController(Screen.Login)

    // Handle System Back Button
    NavBackHandler(enabled = navController.backStack.isNotEmpty()) {
        navController.goBack()
    }

    GEMSTheme {
        CompositionLocalProvider(LocalNavigationController provides navController) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedContent(
                    targetState = navController.currentScreen,
                    transitionSpec = {
                        if (navController.direction == NavDirection.Backward) {
                            // Pop animation: Current slides out to right, New (previous) slides in from left
                            slideInHorizontally(
                                initialOffsetX = { -it / 3 },
                                animationSpec = tween(400)
                            ) + fadeIn() togetherWith
                                    slideOutHorizontally(
                                        targetOffsetX = { it },
                                        animationSpec = tween(400)
                                    ) + fadeOut()
                        } else {
                            // Push animation: Current slides out to left, New slides in from right
                            slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(400)
                            ) + fadeIn() togetherWith
                                    slideOutHorizontally(
                                        targetOffsetX = { -it / 3 },
                                        animationSpec = tween(400)
                                    ) + fadeOut()
                        }
                    }
                ) { screen ->
                    when (screen) {
                        is Screen.Login -> LoginScreen()
                        is Screen.Dashboard -> DashboardScreen()
                        is Screen.Attendance -> AttendanceScreen()
                        is Screen.Marks -> MarksScreen()
                        is Screen.Timetable -> TimetableScreen()
                        is Screen.Notices -> NoticesScreen()
                        is Screen.Profile -> ProfileScreen()
                        is Screen.Settings -> SettingsScreen()
                    }
                }
            }
        }
    }
}