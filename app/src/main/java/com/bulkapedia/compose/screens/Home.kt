package com.bulkapedia.compose.screens

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.LockScreenOrientation
import com.bulkapedia.compose.elements.Toolbar
import com.bulkapedia.compose.elements.topbar.ToolbarViewModel
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.heroes.HeroesNavList
import com.bulkapedia.compose.screens.information.InfoListNav
import com.bulkapedia.compose.screens.login.LoginNav
import com.bulkapedia.compose.screens.profile.ProfileListNav
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.ui.theme.LocalTopBar
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun Home() {
    val ctx = LocalContext.current
    val store = DataStore(ctx)
    val sign by store.getSign.collectAsState(false)
    val email by store.getEmail.collectAsState("")

    val childNC = rememberNavController()
    val isUserSign = sign && email.isNotEmpty()
    val bottomItems = if (isUserSign)
        listOf(Screen.Heroes, Screen.Wiki, Screen.Profile(email))
    else
        listOf(Screen.Heroes, Screen.Wiki, Screen.SignIn)

    val viewModel = hiltViewModel<ToolbarViewModel>()
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    CompositionLocalProvider(
        LocalNavController provides childNC,
        LocalTopBar provides viewModel
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Primary,
            topBar = { Toolbar() },
            bottomBar = { BottomNavigation(
                backgroundColor = PrimaryDark,
                modifier = Modifier.fillMaxWidth()
            ) {
                val navBackStackEntry by childNC.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val previousDestination = remember { mutableStateOf(bottomItems.first().route) }

                bottomItems.map { tab ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                        label = { Text(ctx.getString(tab.title), color = Teal200) },
                        icon = { Image(tab.icon, contentDescription = "",
                            colorFilter = ColorFilter.tint(Teal200)) },
                        onClick = {
                            if (tab.route == previousDestination.value) return@BottomNavigationItem
                            previousDestination.value = tab.route

                            childNC.navigate(tab.route) {
                                popUpTo(childNC.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            } }
        ) {
            NavHost(modifier = Modifier.padding(it), navController = childNC, startDestination = Screen.Heroes.route) {
                composable(Screen.Heroes.route) { HeroesNavList() }
                composable(Screen.Wiki.route) { InfoListNav() }
                if (isUserSign) {
                    composable("${Destinations.PROFILE}/$email") {
                        ProfileListNav("${Destinations.PROFILE}/$email", email)
                    }
                } else {
                    composable(Screen.SignIn.route) { LoginNav() }
                }
            }
        }
    }
}

@Composable
fun Loading() {
    CenteredBox { CircularProgressIndicator(color = Teal200) }
}
