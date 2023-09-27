package vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vopros.bulkapedia.ui.components.topbar.TopBar
import vopros.bulkapedia.ui.navigation.BottomNavigationItem
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.navigation.navLists.CategoriesNavList
import vopros.bulkapedia.ui.navigation.navLists.ProfileNavList

@Composable
fun Home() {
    val navController = rememberNavController()
    val items = listOf(BottomNavigationItem.Wiki, BottomNavigationItem.Profile)
    var currentDestination by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomNavigation {
            items.forEachIndexed { index, item -> BottomNavigationItem(
                selected = index == currentDestination,
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = null) },
                onClick = {
                    currentDestination = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
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