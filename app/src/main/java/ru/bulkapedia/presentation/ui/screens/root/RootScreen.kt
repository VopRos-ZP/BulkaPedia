package ru.bulkapedia.presentation.ui.screens.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.bulkapedia.presentation.ui.screens.Categories
import ru.bulkapedia.presentation.ui.screens.GearPrices
import ru.bulkapedia.presentation.ui.screens.HeroDetails
import ru.bulkapedia.presentation.ui.screens.Heroes
import ru.bulkapedia.presentation.ui.screens.Splash
import ru.bulkapedia.presentation.ui.screens.categories.CategoriesScreen
import ru.bulkapedia.presentation.ui.screens.gear_prices.GearPricesScreen
import ru.bulkapedia.presentation.ui.screens.heroDetails.HeroDetailsScreen
import ru.bulkapedia.presentation.ui.screens.heroes.HeroesScreen
import ru.bulkapedia.presentation.ui.screens.splash.SplashScreen

@Composable
fun RootScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<Splash> {
            SplashScreen(
                onFinish = {
                    navController.navigate(Categories) {
                        launchSingleTop = true
                    }
                }
            )
        }
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
}
