package com.vopros.bulkapedia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vopros.bulkapedia.ui.theme.LocalNavController

@Composable
fun Navigation(
    startDestination: String,
    screens: List<NavigationScreen> = emptyList()
) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = startDestination) {
            screens.forEach { screen ->
                composable(screen.destination, arguments = screen.args) {
                    screen.onNavigate(it.arguments)
                }
            }
        }
    }
}