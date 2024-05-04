package ru.bulkapedia.presentation.ui.screens.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.bulkapedia.presentation.ui.navigation.AppNavGraph
import ru.bulkapedia.presentation.ui.navigation.BottomNavItem
import ru.bulkapedia.presentation.ui.navigation.rememberNavigationState

@Composable
fun RootContent() {
    val navState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navState.navHostController.currentBackStackEntryAsState()
                BottomNavItem.entries.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navState.navigateTo(item.screen.route)
                            }
                        },
                        icon = { Icon(imageVector = item.image, contentDescription = null) }
                    )
                }
            }
        }
    ) {
        AppNavGraph(
            navHostController = navState.navHostController,
            modifier = Modifier.padding(it)
        )
    }
}
