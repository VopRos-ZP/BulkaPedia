package com.vopros.bulkapedia.ui.navigation

import androidx.navigation.NavType
import com.vopros.bulkapedia.ui.screens.categories.CategoriesScreen
import com.vopros.bulkapedia.ui.screens.hero.HeroScreen
import com.vopros.bulkapedia.ui.screens.heroes.HeroesScreen
import com.vopros.bulkapedia.ui.screens.login.LoginScreen
import com.vopros.bulkapedia.ui.screens.maps.MapsScreen
import com.vopros.bulkapedia.ui.screens.profile.ProfileScreen
import com.vopros.bulkapedia.ui.screens.profileController.ProfileControllerScreen

val ToCategories = NavigationScreen(Destinations.CATEGORIES) { CategoriesScreen() }
val ToHeroes = NavigationScreen(Destinations.HEROES) { HeroesScreen() }
val ToMaps = NavigationScreen(Destinations.MAPS) { MapsScreen() }
val ToLogin = NavigationScreen(Destinations.LOGIN) { LoginScreen() }
val ToProfileController = NavigationScreen(Destinations.PROFILE) { ProfileControllerScreen() }

val ToProfile = NavigationScreen(
    "${Destinations.PROFILE}/{userId}",
    listOf(navArg("userId", NavType.StringType))
) { it?.getString("userId")?.let { id -> ProfileScreen(id) } }

val ToHero = NavigationScreen(
    "${Destinations.HERO}{heroId}",
    listOf(navArg("heroId", NavType.StringType))
) { HeroScreen(it?.getString("heroId")!!) }
