@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun FirstLoginBlock(content: @Composable ColumnScope.() -> Unit) {
    LoginBlock(50.dp, content)
}

@Composable
fun LoginBlock(
    top: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Block(marginTop = top, marginBottom = 20.dp, content)
}

@Composable
fun Block(
    marginTop: Dp,
    marginBottom: Dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Block(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = marginBottom, top = marginTop)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .border(2.dp, Teal200, RoundedCornerShape(20.dp))
            .padding(20.dp),
        content
    )
}

@Composable
fun Block(
    marginStart: Dp,
    marginEnd: Dp,
    marginTop: Dp,
    marginBottom: Dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Block(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = marginStart, end = marginEnd, bottom = marginBottom, top = marginTop)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .border(2.dp, Teal200, RoundedCornerShape(20.dp))
            .padding(20.dp),
        content
    )
}

@Composable
fun Block(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier, content = content)
}
