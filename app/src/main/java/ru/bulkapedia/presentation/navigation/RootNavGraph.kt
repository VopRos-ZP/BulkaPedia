package ru.bulkapedia.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.bulkapedia.presentation.screens.categories.CategoriesScreen
import ru.bulkapedia.presentation.screens.notifications.NotificationsScreen

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    isLogin: Boolean = false,
    navController: NavHostController,
    onBack: () -> Unit,
    onNavigate: (Screen) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.CategoriesFlow.route,
    ) {
        categoriesNavGraph(
            onBack = onBack,
            onNavigate = onNavigate
        )
        composable(route = Screen.Notifications.route) {
            NotificationsScreen(onNavigate = onNavigate)
        }
        when (isLogin) {
            true -> profileNavGraph(

                onNavigate = onNavigate
            )
            else -> loginNavGraph(
                onBack = onBack,
                onNavigate = onNavigate
            )
        }
    }
}