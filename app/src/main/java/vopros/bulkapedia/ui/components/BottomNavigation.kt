package vopros.bulkapedia.ui.components

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.ramcosta.composedestinations.navigation.navigate
import vopros.bulkapedia.ui.navigation.BottomNavigationItem
import vopros.bulkapedia.ui.screens.NavGraphs
import vopros.bulkapedia.ui.screens.appCurrentDestinationAsState
import vopros.bulkapedia.ui.screens.startAppDestination
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun BottomNavigation(navController: NavController) {
    val currentDestination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination
    androidx.compose.material.BottomNavigation(
        backgroundColor = BulkaPediaTheme.colors.primary,
    ) {
        BottomNavigationItem.values().map { item ->
            BottomNavigationItem(
                selected = currentDestination == item.direction,
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = null, tint = BulkaPediaTheme.colors.tintColor) },
                onClick = {
                    navController.navigate(item.direction) {
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
