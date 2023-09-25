package com.vopros.bulkapedia.ui.navigation

import androidx.navigation.NavType
import com.vopros.bulkapedia.ui.screens.categories.CategoriesScreen
import com.vopros.bulkapedia.ui.screens.hero.HeroScreen
import com.vopros.bulkapedia.ui.screens.heroes.HeroesScreen
import com.vopros.bulkapedia.ui.screens.maps.MapsScreen

val ToCategories = NavigationScreen(Destinations.CATEGORIES) { CategoriesScreen() }
val ToHeroes = NavigationScreen(Destinations.HEROES) { HeroesScreen() }
val ToMaps = NavigationScreen(Destinations.MAPS) { MapsScreen() }

val ToHero = NavigationScreen(
    "${Destinations.HERO}{heroId}",
    listOf(navArg("heroId", NavType.StringType))
) { HeroScreen(it?.getString("heroId")!!) }
