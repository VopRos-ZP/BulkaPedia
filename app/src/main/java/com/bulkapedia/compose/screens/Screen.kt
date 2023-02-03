package com.bulkapedia.compose.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulkapedia.R
import com.bulkapedia.compose.navigation.Destinations

sealed class Screen (val route: String, val icon: ImageVector, val title: Int) {
    object Heroes: Screen(Destinations.HEROES, Icons.Default.Home, R.string.menu_gears)
    object Maps: Screen(Destinations.MAPS, Icons.Default.Map, R.string.menu_maps)
    object SignIn: Screen(Destinations.SING_IN,Icons.Default.Person, R.string.login)
    data class Profile(val email: String): Screen(
        "${Destinations.PROFILE}/$email", Icons.Default.Person, R.string.profile)
}