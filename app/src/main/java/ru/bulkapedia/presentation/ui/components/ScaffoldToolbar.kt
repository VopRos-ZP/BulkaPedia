package ru.bulkapedia.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun ScaffoldToolbar(
    @StringRes id: Int,
    navController: NavController? = null,
    content: @Composable BoxScope.() -> Unit
) {
    ScaffoldToolbar(
        text = stringResource(id),
        navController = navController,
        content = content
    )
}

@Composable
fun ScaffoldToolbar(
    text: String,
    navController: NavController? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold(topBar = { Toolbar(text, navController) }) {
        Box(
            modifier = Modifier.padding(it),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}