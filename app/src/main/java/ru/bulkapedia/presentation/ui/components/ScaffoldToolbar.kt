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

@Composable
fun ScaffoldToolbar(
    @StringRes id: Int,
    content: @Composable BoxScope.() -> Unit
) {
    ScaffoldToolbar(
        text = stringResource(id),
        content = content
    )
}

@Composable
fun ScaffoldToolbar(
    text: String,
    content: @Composable BoxScope.() -> Unit
) {

}