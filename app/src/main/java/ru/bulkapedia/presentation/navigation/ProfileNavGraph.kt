package ru.bulkapedia.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.profileNavGraph(
    onNavigate: (Screen) -> Unit
) {
    navigation(
        startDestination = Screen.Profile.route,
        route = Screen.ProfileFlow.route
    ) {
        composable(route = Screen.Profile.route) {

        }
    }
}