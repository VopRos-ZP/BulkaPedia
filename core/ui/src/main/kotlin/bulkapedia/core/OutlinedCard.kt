package bulkapedia.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import bulkapedia.core.theme.PrimaryDark
import bulkapedia.core.theme.Teal200

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
    Card(
        modifier = modifier.clickable { onClick() },
        backgroundColor = backgroundColor,
        shape = shape,
        border = border,
        content = content
    )
}