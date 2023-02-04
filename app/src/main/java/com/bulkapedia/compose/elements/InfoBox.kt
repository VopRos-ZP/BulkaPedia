@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.util.CenteredBox

@Preview(showBackground = true)
@Composable
fun Preview() {
    CenteredBox (
        modifier = Modifier.fillMaxSize()
            .background(PrimaryDark)
    ) {
        InfoBox(text = "Последущая смена ника будет возможна только через 2 месяца")
    }
}

@Composable
fun InfoBox(text: String) {
    Row (
        modifier = Modifier.fillMaxWidth()
            .background(Color.Transparent, RoundedCornerShape(10.dp))
            .border(1.dp, Color.Yellow, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = "info",
            tint = Color.Yellow,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = text,
            color = Color.Yellow
        )
    }
}