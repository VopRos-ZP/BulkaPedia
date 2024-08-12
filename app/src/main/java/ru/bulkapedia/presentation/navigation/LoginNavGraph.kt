package ru.bulkapedia.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.loginNavGraph(
    onNavigate: (Screen) -> Unit
) {
    navigation(
        startDestination = Screen.Login.route,
        route = Screen.LoginFlow.route
    ) {
        composable(route = Screen.Login.route) {

        }
    }
}