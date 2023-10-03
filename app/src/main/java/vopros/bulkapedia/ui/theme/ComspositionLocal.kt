package vopros.bulkapedia.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import vopros.bulkapedia.ui.components.topbar.TopBarViewModel

val LocalBulkaPediaColors = staticCompositionLocalOf<BulkaPediaColors> {
    error("BulkaPediaColors doesn't provides")
}

val LocalNavController = compositionLocalOf<NavController> {
    error("NavController doesn't provides")
}

val LocalTopBarViewModel = compositionLocalOf<TopBarViewModel> {
    error("TopBarViewModel doesn't provides")
}