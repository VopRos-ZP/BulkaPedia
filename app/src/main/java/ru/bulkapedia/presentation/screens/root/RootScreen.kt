package ru.bulkapedia.presentation.screens.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.bulkapedia.presentation.components.BottomNavBar
import ru.bulkapedia.presentation.navigation.RootNavGraph

@Composable
fun RootScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavBar(navController) }
    ) {
        RootNavGraph(
            modifier = Modifier.padding(it),
            navController = navController,
            onNavigate = { screen -> navController.navigate(screen.route) }
        )
    }
}