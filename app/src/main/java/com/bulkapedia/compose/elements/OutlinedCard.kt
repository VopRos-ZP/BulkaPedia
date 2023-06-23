package com.bulkapedia.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.clickable

@Composable
fun OutlinedCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = PrimaryDark,
    shape: Shape = RoundedCornerShape(20.dp),
    borderColor: Color = Teal200,
    border: BorderStroke = BorderStroke(2.dp, borderColor),
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Card (
        modifier = modifier.clickable(onClick),
        backgroundColor = backgroundColor,
        shape = shape,
        border = border,
        content = content
    )
}