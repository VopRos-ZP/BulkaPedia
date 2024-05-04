package ru.bulkapedia.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.bulkapedia.presentation.ui.screens.categories.CategoriesScreen
import ru.bulkapedia.presentation.ui.screens.login.LoginScreen
import ru.bulkapedia.presentation.ui.screens.maps.MapsScreen
import ru.bulkapedia.presentation.ui.screens.registration.RegistrationScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Wiki.route,
        modifier = modifier
    ) {
        wikiNavGraph(navHostController)
        authNavGraph(navHostController)
        // profile
//        navigation(startDestination = Screen.Profile.route,  route = Screen.Profile.route) {
//
//        }
    }
}