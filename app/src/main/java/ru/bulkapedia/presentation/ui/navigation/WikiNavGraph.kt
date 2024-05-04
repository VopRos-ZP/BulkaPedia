package ru.bulkapedia.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import ru.bulkapedia.presentation.ui.screens.categories.CategoriesScreen
import ru.bulkapedia.presentation.ui.screens.map.MapScreen
import ru.bulkapedia.presentation.ui.screens.maps.MapsScreen
import ru.bulkapedia.presentation.ui.screens.sets.SetsScreen

fun NavGraphBuilder.wikiNavGraph(navHostController: NavHostController) {
    navigation(startDestination = Screen.Categories.route,  route = Screen.Wiki.route) {
        composable(route = Screen.Categories.route) {
            CategoriesScreen(navHostController)
        }
        composable(route = Screen.Maps.route) {
            MapsScreen(navHostController)
        }
        composable(
            route = "${Screen.Maps.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            MapScreen(navHostController)
        }
        composable(route = Screen.Sets.route) {
            SetsScreen(navHostController)
        }
    }
}