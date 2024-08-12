package ru.bulkapedia.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.bulkapedia.presentation.navigation.Screen

@Composable
fun BottomNavBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomItems = listOf(
        Screen.CategoriesFlow to Icons.Default.Home,
        Screen.Notifications to Icons.Default.Notifications,
        Screen.LoginFlow to Icons.AutoMirrored.Filled.Login
    )
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        bottomItems.map { (screen, icon) ->
            NavigationBarItem(
                selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } ?: false,
                onClick = {
                    navController.navigate(route = screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(icon, contentDescription = null) },
                alwaysShowLabel = false
            )
        }
    }
}