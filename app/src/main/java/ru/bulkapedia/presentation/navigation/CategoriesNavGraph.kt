package ru.bulkapedia.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.bulkapedia.presentation.navigation.Screen.Companion.ROUTE_SETS
import ru.bulkapedia.presentation.screens.categories.CategoriesScreen
import ru.bulkapedia.presentation.screens.sets.SetsScreen

fun NavGraphBuilder.categoriesNavGraph(
    onBack: () -> Unit,
    onNavigate: (Screen) -> Unit
) {
    navigation(
        startDestination = Screen.Categories.route,
        route = Screen.CategoriesFlow.route
    ) {
        composable(route = Screen.Categories.route) {
            CategoriesScreen(onNavigate = onNavigate)
        }
        composable(route = Screen.CategoryOf(ROUTE_SETS).route) {
            SetsScreen(
                onClick = onBack,
                onNavigate = onNavigate
            )
        }
    }
}
