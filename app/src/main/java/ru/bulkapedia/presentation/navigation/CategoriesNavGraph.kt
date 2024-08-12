package ru.bulkapedia.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.bulkapedia.presentation.screens.categories.CategoriesScreen

fun NavGraphBuilder.categoriesNavGraph(
    onNavigate: (Screen) -> Unit
) {
    navigation(
        startDestination = Screen.Categories.route,
        route = Screen.CategoriesFlow.route
    ) {
        composable(route = Screen.Categories.route) {
            CategoriesScreen(onNavigate = onNavigate)
        }
    }
}