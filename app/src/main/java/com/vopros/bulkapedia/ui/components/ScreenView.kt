package com.vopros.bulkapedia.ui.components

import androidx.compose.runtime.Composable
import com.vopros.bulkapedia.ui.theme.LocalNavController
import com.vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@Composable
fun ScreenView(
    title: String, showBack: Boolean = false,
    content: @Composable () -> Unit
) {
    val viewModel = LocalTopBarViewModel.current
    val navController = LocalNavController.current
    viewModel.update(title, showBack, navController)
    content()
}