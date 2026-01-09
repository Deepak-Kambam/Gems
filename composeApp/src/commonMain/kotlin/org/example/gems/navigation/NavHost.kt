package org.example.gems.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

enum class NavDirection {
    Forward, Backward, None
}

sealed class Screen(val id: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object Attendance : Screen("attendance")
    object Marks : Screen("marks")
    object Timetable : Screen("timetable")
    object Notices : Screen("notices")
    object Profile : Screen("profile")
    object Settings : Screen("settings")

    companion object {
        fun fromId(id: String?): Screen = when (id) {
            "login" -> Login
            "dashboard" -> Dashboard
            "attendance" -> Attendance
            "marks" -> Marks
            "timetable" -> Timetable
            "notices" -> Notices
            "profile" -> Profile
            "settings" -> Settings
            else -> Login
        }
    }
}

class NavigationController(initialScreen: Screen = Screen.Login) {
    var currentScreen by mutableStateOf(initialScreen)
    var direction by mutableStateOf(NavDirection.None)
    
    val backStack = mutableStateListOf<Screen>()

    fun navigateTo(screen: Screen) {
        if (currentScreen != screen) {
            direction = NavDirection.Forward
            backStack.add(currentScreen)
            currentScreen = screen
        }
    }

    fun clearAndNavigate(screen: Screen) {
        direction = NavDirection.Forward
        backStack.clear()
        currentScreen = screen
    }

    fun goBack() {
        if (backStack.isNotEmpty()) {
            direction = NavDirection.Backward
            currentScreen = backStack.removeAt(backStack.size - 1)
        }
    }

    fun resetToLogin() {
        direction = NavDirection.Backward
        backStack.clear()
        currentScreen = Screen.Login
    }

    companion object {
        val Saver: Saver<NavigationController, *> = listSaver(
            save = { listOf(it.currentScreen.id) + it.backStack.map { screen -> screen.id } },
            restore = { list ->
                val controller = NavigationController(Screen.fromId(list[0]))
                list.drop(1).forEach { controller.backStack.add(Screen.fromId(it)) }
                controller
            }
        )
    }
}

@Composable
fun rememberNavigationController(initialScreen: Screen = Screen.Login): NavigationController {
    return rememberSaveable(saver = NavigationController.Saver) {
        NavigationController(initialScreen)
    }
}

@Composable
expect fun NavBackHandler(enabled: Boolean, onBack: () -> Unit)

val LocalNavigationController = compositionLocalOf<NavigationController> {
    error("No NavigationController provided")
}
