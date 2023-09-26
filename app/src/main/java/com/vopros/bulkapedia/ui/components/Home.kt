package com.vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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
import com.vopros.bulkapedia.ui.components.topbar.TopBar
import com.vopros.bulkapedia.ui.navigation.BottomNavigationItem
import com.vopros.bulkapedia.ui.navigation.Destinations
import com.vopros.bulkapedia.ui.navigation.navLists.CategoriesNavList
import com.vopros.bulkapedia.ui.navigation.navLists.ProfileNavList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val navController = rememberNavController()
    val items = listOf(BottomNavigationItem.Wiki, BottomNavigationItem.Profile)
    var currentDestination by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { NavigationBar {
            items.forEachIndexed { index, item -> NavigationBarItem(
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