package ru.bulkapedia.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ru.bulkapedia.presentation.navigation.MainNavGraph
import ru.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    BulkaPediaTheme {
        MainNavGraph(navController = navController) { screen ->
            navController.navigate(route = screen.route)
        }
    }
}