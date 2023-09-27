package vopros.bulkapedia.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import vopros.bulkapedia.ui.components.topbar.TopBarViewModel

val LocalNavController = compositionLocalOf<NavController> {
    error("NavController doesn't provides")
}

val LocalTopBarViewModel = compositionLocalOf<TopBarViewModel> {
    error("TopBarViewModel doesn't provides")
}