package ru.bulkapedia.presentation.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.bulkapedia.presentation.ui.screens.App
import ru.bulkapedia.presentation.ui.screens.Auth
import ru.bulkapedia.presentation.ui.screens.Categories
import ru.bulkapedia.presentation.ui.screens.GearPrices
import ru.bulkapedia.presentation.ui.screens.HeroDetails
import ru.bulkapedia.presentation.ui.screens.Heroes
import ru.bulkapedia.presentation.ui.screens.auth.AuthScreen
import ru.bulkapedia.presentation.ui.screens.categories.CategoriesScreen
import ru.bulkapedia.presentation.ui.screens.gear_prices.GearPricesScreen
import ru.bulkapedia.presentation.ui.screens.heroDetails.HeroDetailsScreen
import ru.bulkapedia.presentation.ui.screens.heroes.HeroesScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navItems = listOf(
        TopLevelRoute(App, Icons.Default.Apps),
        TopLevelRoute(Auth, Icons.AutoMirrored.Filled.Login),
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navItems.map { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } ?: false,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.icon.name
                            )
                        },
                        alwaysShowLabel = false,
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Categories,
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation<App>(
                startDestination = Categories
            ) {
                composable<Categories> {
                    CategoriesScreen(
                        onCategoryClick = {
                            when (it) {
                                "GearPrices" -> navController.navigate(GearPrices)
                                "Heroes" -> navController.navigate(Heroes)
                            }
                        }
                    )
                }
                composable<GearPrices> {
                    GearPricesScreen(
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
                composable<Heroes> {
                    HeroesScreen(
                        onBackClick = {
                            navController.navigateUp()
                        },
                        onHeroClick = {
                            navController.navigate(HeroDetails(it))
                        }
                    )
                }
                composable<HeroDetails> {
                    val route = it.toRoute<HeroDetails>()
                    HeroDetailsScreen(
                        viewModel = koinViewModel(parameters = {
                            parametersOf(route.id)
                        }),
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
            composable<Auth> {
                AuthScreen()
            }
        }
    }
}