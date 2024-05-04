package ru.bulkapedia.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.bulkapedia.presentation.ui.screens.login.LoginScreen
import ru.bulkapedia.presentation.ui.screens.registration.RegistrationScreen

fun NavGraphBuilder.profileNavGraph(navHostController: NavHostController) {
    navigation(startDestination = Screen.Profile.route, route = Screen.Auth.route) {
        composable(route = Screen.Login.route) {
            LoginScreen(navHostController)
        }
        composable(route = Screen.Registration.route) {
            RegistrationScreen(navHostController)
        }
    }
}