package vopros.bulkapedia.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlinedCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    spacedBy: Dp = 5.dp,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit
) {
    androidx.compose.material.Card(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        modifier = modifier,
        backgroundColor = Color.Transparent,
        border = BorderStroke(2.dp, BulkaPediaTheme.colors.tintColor)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = horizontal,
            verticalArrangement = Arrangement.spacedBy(spacedBy),
            content = content
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Card(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    spacedBy: Dp = 5.dp,
    radius: Dp = 5.dp,
    color: Color = BulkaPediaTheme.colors.primary,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit
) {
    androidx.compose.material.Card(
        onClick = onClick,
        backgroundColor = color,
        shape = RoundedCornerShape(radius),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = horizontal,
            verticalArrangement = Arrangement.spacedBy(spacedBy),
            content = content
        )
    }
}
