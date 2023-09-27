package vopros.bulkapedia.ui.navigation.navLists

import androidx.compose.runtime.Composable
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.navigation.Navigation
import vopros.bulkapedia.ui.navigation.ToCategories
import vopros.bulkapedia.ui.navigation.ToCreateSet
import vopros.bulkapedia.ui.navigation.ToHero
import vopros.bulkapedia.ui.navigation.ToHeroes
import vopros.bulkapedia.ui.navigation.ToLogin
import vopros.bulkapedia.ui.navigation.ToMap
import vopros.bulkapedia.ui.navigation.ToMaps
import vopros.bulkapedia.ui.navigation.ToProfile
import vopros.bulkapedia.ui.navigation.ToProfileController
import vopros.bulkapedia.ui.navigation.ToSetting

@Composable
fun CategoriesNavList() {
    Navigation(
        startDestination = Destinations.CATEGORIES,
        screens = listOf(
            /* Wiki Tab */
            ToCategories,
            ToHeroes, ToHero,
            ToMaps, ToMap,
            ToCreateSet,
            /* Profile */
            ToProfileController,
            /* Settings */
            ToSetting,
        )
    )
}

@Composable
fun ProfileNavList() {
    Navigation(
        startDestination = Destinations.PROFILE,
        screens = listOf(
            /* Profile Tab */
            ToProfileController,
            ToLogin, ToProfile,
            ToCreateSet,
            /* Wiki Tab */
            ToCategories,
            /* Settings */
            ToSetting,
        )
    )
}