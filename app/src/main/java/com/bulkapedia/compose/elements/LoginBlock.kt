@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
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

@Composable
fun FirstLoginBlock(
    content: @Composable ColumnScope.() -> Unit
) {
    LoginBlock(50.dp, content)
}

@Composable
fun LoginBlock(
    top: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = top)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .padding(20.dp),
        content = content
    )
}
