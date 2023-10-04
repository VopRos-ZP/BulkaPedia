package vopros.bulkapedia.ui.components

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import vopros.bulkapedia.ui.navigation.BottomNavigationItem
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(BottomNavigationItem.Wiki, BottomNavigationItem.Profile)
    androidx.compose.material.BottomNavigation(
        backgroundColor = BulkaPediaTheme.colors.primary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val previousDestination = remember { mutableStateOf(items.first().route) }
        items.map { item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = null, tint = BulkaPediaTheme.colors.tintColor) },
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
                }
            )
        }
    }
}
