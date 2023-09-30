package vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import vopros.bulkapedia.ui.components.topbar.TopBar
import vopros.bulkapedia.ui.navigation.BottomNavigationItem
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.navigation.navLists.CategoriesNavList
import vopros.bulkapedia.ui.navigation.navLists.ProfileNavList
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@Composable
fun Home() {
    val navController = rememberNavController()
    val items = listOf(BottomNavigationItem.Wiki, BottomNavigationItem.Profile)
    CompositionLocalProvider(
        LocalTopBarViewModel provides hiltViewModel(),
        LocalNavController provides navController
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = { BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val previousDestination = remember { mutableStateOf(items.first().route) }
                items.map { item -> BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    label = { Text(item.label) },
                    icon = { Icon(item.icon, contentDescription = null) },
                    onClick = {
                        if (item.route == previousDestination.value) return@BottomNavigationItem
                        previousDestination.value = item.route
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
                } } }
        ) {
            NavHost(modifier = Modifier.padding(it), navController = navController, startDestination = Destinations.WIKI) {
                composable(route = Destinations.WIKI) { CategoriesNavList() }
                composable(route = Destinations.PROFILE) { ProfileNavList() }
            }
        }
    }
}