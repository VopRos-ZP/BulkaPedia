package com.bulkapedia.compose.screens.titled

import androidx.compose.runtime.Composable
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.ui.theme.LocalTopBar

@Composable
fun ScreenView(
    title: String, showBack: Boolean = false,
    content: @Composable () -> Unit
) {
    val viewModel = LocalTopBar.current
    val navController = LocalNavController.current
    viewModel.update(title, showBack, navController)
    content()
}