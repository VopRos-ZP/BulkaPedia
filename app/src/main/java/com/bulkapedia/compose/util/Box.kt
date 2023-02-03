package com.bulkapedia.compose.util

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.modifier.modifierLocalConsumer

@Composable
fun CenteredBox(boxScope: @Composable BoxScope.() -> Unit) {
    CenteredBox(modifier = Modifier.fillMaxSize(), boxScope = boxScope)
}

@Composable
fun CenteredBox(modifier: Modifier = Modifier, boxScope: @Composable BoxScope.() -> Unit) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = boxScope
    )
}

@Composable
fun HCenteredBox(boxScope: @Composable BoxScope.() -> Unit) {
    HCenteredBox(modifier = Modifier.fillMaxWidth(), boxScope = boxScope)
}

@Composable
fun HCenteredBox(modifier: Modifier = Modifier, boxScope: @Composable BoxScope.() -> Unit) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = boxScope
    )
}

@Composable
fun VCenteredBox(boxScope: @Composable BoxScope.() -> Unit) {
    VCenteredBox(Modifier.fillMaxHeight(), boxScope)
}

@Composable
fun VCenteredBox(modifier: Modifier, boxScope: @Composable BoxScope.() -> Unit) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = boxScope
    )
}