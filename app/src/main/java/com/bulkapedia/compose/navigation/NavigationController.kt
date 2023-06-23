package com.bulkapedia.compose.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(
    startDestination: String,
    screens: List<NavigationScreen> = emptyList()
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        screens.forEach { screen ->
            composable(screen.destination, arguments = screen.args) {
                screen.onNavigate.invoke(it.arguments)
            }
        }
    }
}

fun NavController.navigate(route: String, params: Bundle = bundleOf(), builder: NavOptionsBuilder.() -> Unit = {}) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(route, builder)
}
