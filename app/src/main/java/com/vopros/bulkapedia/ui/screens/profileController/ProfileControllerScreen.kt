package com.vopros.bulkapedia.ui.screens.profileController

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.navigation.Destinations
import com.vopros.bulkapedia.ui.screens.Screen
import com.vopros.bulkapedia.ui.theme.LocalNavController

@Composable
fun ProfileControllerScreen() {
    val controller = LocalNavController.current
    Screen<Pair<String, Boolean>, ProfileControllerViewModel>(
        title = R.string.empty,
        fetch = { startIntent(ProfileControllerViewIntent.Start) }
    ) { _, pair ->
        LaunchedEffect(pair) {
            if (pair.second) {
                controller.navigate("${Destinations.PROFILE}/${pair.first}")
            } else {
                controller.navigate(Destinations.LOGIN)
            }
        }
    }
}