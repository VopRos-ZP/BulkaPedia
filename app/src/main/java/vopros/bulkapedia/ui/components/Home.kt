package vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vopros.bulkapedia.ui.components.topbar.TopBar
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.navigation.navLists.CategoriesNavList
import vopros.bulkapedia.ui.navigation.navLists.ProfileNavList
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@Composable
fun Home() {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalTopBarViewModel provides hiltViewModel(),
        LocalNavController provides navController
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = { BottomNavigation(navController) }
        ) {
            NavHost(modifier = Modifier.padding(it), navController = navController, startDestination = Destinations.WIKI) {
                composable(route = Destinations.WIKI) { CategoriesNavList() }
                composable(route = Destinations.PROFILE) { ProfileNavList() }
            }
        }
    }
}