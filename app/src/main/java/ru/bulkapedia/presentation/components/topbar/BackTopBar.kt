package ru.bulkapedia.presentation.components.topbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun BackTopBar(
    onClick: () -> Unit,
    @StringRes text: Int
) {
    BackTopBar(
        onClick = onClick,
        text = stringResource(id = text)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopBar(
    onClick: () -> Unit,
    text: String,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(Icons.AutoMirrored.Default.KeyboardArrowLeft, contentDescription = null)
            }
        },
        title = { Text(text = text) }
    )
}