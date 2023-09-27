package com.vopros.bulkapedia.ui.navigation

import androidx.navigation.NavType
import com.vopros.bulkapedia.ui.screens.categories.CategoriesScreen
import com.vopros.bulkapedia.ui.screens.createSet.CreateSetScreen
import com.vopros.bulkapedia.ui.screens.hero.HeroScreen
import com.vopros.bulkapedia.ui.screens.heroes.HeroesScreen
import com.vopros.bulkapedia.ui.screens.login.LoginScreen
import com.vopros.bulkapedia.ui.screens.map.MapScreen
import com.vopros.bulkapedia.ui.screens.maps.MapsScreen
import com.vopros.bulkapedia.ui.screens.profile.ProfileScreen
import com.vopros.bulkapedia.ui.screens.profileController.ProfileControllerScreen
import com.vopros.bulkapedia.ui.screens.settings.SettingsScreen

val ToCategories = NavigationScreen(Destinations.CATEGORIES) { CategoriesScreen() }
val ToHeroes = NavigationScreen(Destinations.HEROES) { HeroesScreen() }
val ToMaps = NavigationScreen(Destinations.MAPS) { MapsScreen() }
val ToLogin = NavigationScreen(Destinations.LOGIN) { LoginScreen() }
val ToProfileController = NavigationScreen(Destinations.PROFILE) { ProfileControllerScreen() }
val ToSetting = NavigationScreen(Destinations.SETTINGS) { SettingsScreen() }

val ToMap = NavigationScreen(
    "${Destinations.MAP}{mapId}",
    listOf(navArg("mapId", NavType.StringType))
) { it?.getString("mapId")?.let { id -> MapScreen(id) } }

val ToProfile = NavigationScreen(
    "${Destinations.PROFILE}/{userId}",
    listOf(navArg("userId", NavType.StringType))
) { it?.getString("userId")?.let { id -> ProfileScreen(id) } }

val ToHero = NavigationScreen(
    "${Destinations.HERO}{heroId}",
    listOf(navArg("heroId", NavType.StringType))
) { HeroScreen(it?.getString("heroId")!!) }

val ToCreateSet = NavigationScreen(
    "${Destinations.CREATE_SET}/{heroId}?setId={setId}",
    listOf(
        navArg("heroId", NavType.StringType),
        navArg("setId", NavType.StringType, null),
    )
) {
    CreateSetScreen(
        heroId = it?.getString("heroId")!!,
        setId = it.getString("setId")
    )
}