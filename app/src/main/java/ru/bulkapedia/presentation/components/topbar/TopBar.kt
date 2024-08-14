package ru.bulkapedia.presentation.components.topbar

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun TopBar(@StringRes text: Int) {
    TopBar(text = stringResource(id = text))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(text: String) {
    CenterAlignedTopAppBar(title = { Text(text = text) })
}