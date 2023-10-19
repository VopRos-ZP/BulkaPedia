package vopros.bulkapedia.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.ui.theme.BulkapediaTheme

@Preview(showBackground = true)
@Composable
fun OutlinedButton_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primary) {
            OutlinedButton(onClick = { /*TODO*/ }, text = R.string.show_spawns)
        }
    }
}

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    text: Int,
    modifier: Modifier = Modifier,
    bgColor: Color = Color.Transparent,
    color: Color = BulkaPediaTheme.colors.tintColor,
    enabled: Boolean = true,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        bgColor = bgColor,
        color = color,
        enabled = enabled
    ) { Text(text, color = color) }
}

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    bgColor: Color = Color.Transparent,
    color: Color = BulkaPediaTheme.colors.tintColor,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    androidx.compose.material.OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(2.dp, color),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = bgColor
        ),
        enabled = enabled
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}