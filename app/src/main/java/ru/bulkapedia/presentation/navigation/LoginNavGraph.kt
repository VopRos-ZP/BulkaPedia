package ru.bulkapedia.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.bulkapedia.presentation.screens.login.LoginScreen
import ru.bulkapedia.presentation.screens.registration.RegistrationScreen

fun NavGraphBuilder.loginNavGraph(
    onBack: () -> Unit,
    onNavigate: (Screen) -> Unit
) {
    navigation(
        startDestination = Screen.Login.route,
        route = Screen.LoginFlow.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(onNavigate = onNavigate)
        }
        composable(route = Screen.Registration.route) {
            RegistrationScreen(onClick = onBack)
        }
    }
}