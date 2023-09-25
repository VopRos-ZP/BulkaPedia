package com.vopros.bulkapedia.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.vopros.bulkapedia.R

sealed class BottomNavigationItem(val icon: ImageVector, val label: Int, val route: String) {
    object Wiki: BottomNavigationItem(Icons.Filled.Home, R.string.wiki, Destinations.WIKI)
    object Profile: BottomNavigationItem(Icons.Filled.Person, R.string.profile, Destinations.PROFILE)
}