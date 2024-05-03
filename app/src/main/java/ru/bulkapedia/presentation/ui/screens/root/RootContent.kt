package ru.bulkapedia.presentation.ui.screens.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Window
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.bulkapedia.presentation.def.DefaultRootComponent
import ru.bulkapedia.presentation.screens.Screen
import ru.bulkapedia.presentation.ui.navigation.AppNavGraph

@Composable
fun RootContent() {
    val navHostController = rememberNavController()

    val items = listOf(
        Screen.WIKI to Icons.Filled.Book,
        Screen.TOURNAMENTS to Icons.Outlined.SportsEsports,
        Screen.AUTH to Icons.AutoMirrored.Filled.Login
    )
    Scaffold(
        topBar = {},
        bottomBar = {
            BottomNavigationBar(
                navHostController = navHostController,
                items = items
            )
        }
    ) {
        AppNavGraph(
            navHostController = navHostController,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController,
    items: List<Pair<Screen, ImageVector>>
) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEach { (screen, icon) ->
            NavigationBarItem(
                selected = backStackEntry?.destination?.route == screen.route,
                onClick = { navHostController.navigate(screen.route) },
                icon = { Icon(imageVector = icon, contentDescription = null) }
            )
        }
    }
}