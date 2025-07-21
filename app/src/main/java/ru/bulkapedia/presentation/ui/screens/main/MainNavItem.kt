package ru.bulkapedia.presentation.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Apps
import androidx.compose.ui.graphics.vector.ImageVector
import ru.bulkapedia.presentation.ui.screens.App
import ru.bulkapedia.presentation.ui.screens.Auth

sealed class MainNavItem(
    val icon: ImageVector,
    val route: Any
) {

    data object AppNavItem : MainNavItem(Icons.Default.Apps, route = App)
    data object AuthNavItem : MainNavItem(Icons.AutoMirrored.Filled.Login, route = Auth)

}

data class TopLevelRoute<T : Any>(val route: T, val icon: ImageVector)
