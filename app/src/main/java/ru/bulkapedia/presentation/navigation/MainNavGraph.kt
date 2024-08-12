package ru.bulkapedia.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.bulkapedia.presentation.screens.root.RootScreen
import ru.bulkapedia.presentation.screens.splash.SplashScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    onNavigate: (Screen) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(onNavigate = onNavigate)
        }
        composable(route = Screen.Root.route) {
            RootScreen()
        }
    }
}