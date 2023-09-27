package com.vopros.bulkapedia.ui.navigation.navLists

import androidx.compose.runtime.Composable
import com.vopros.bulkapedia.ui.navigation.Destinations
import com.vopros.bulkapedia.ui.navigation.Navigation
import com.vopros.bulkapedia.ui.navigation.ToCategories
import com.vopros.bulkapedia.ui.navigation.ToCreateSet
import com.vopros.bulkapedia.ui.navigation.ToHero
import com.vopros.bulkapedia.ui.navigation.ToHeroes
import com.vopros.bulkapedia.ui.navigation.ToLogin
import com.vopros.bulkapedia.ui.navigation.ToMap
import com.vopros.bulkapedia.ui.navigation.ToMaps
import com.vopros.bulkapedia.ui.navigation.ToProfile
import com.vopros.bulkapedia.ui.navigation.ToProfileController
import com.vopros.bulkapedia.ui.navigation.ToSetting

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