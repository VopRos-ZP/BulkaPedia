package ru.bulkapedia.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.bulkapedia.presentation.screens.Screen
import ru.bulkapedia.presentation.ui.screens.categories.CategoriesScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.WIKI.route,
        modifier = modifier
    ) {
        composable(Screen.WIKI.route) {
            //CategoriesScreen()
        }
        composable(Screen.TOURNAMENTS.route) {

        }
        composable(Screen.AUTH.route) {

        }
    }
}