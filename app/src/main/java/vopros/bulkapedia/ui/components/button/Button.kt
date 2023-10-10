package vopros.bulkapedia.ui.components.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.stringResource
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = BulkaPediaTheme.colors.primaryDark,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = BulkaPediaTheme.colors.primaryDark.copy(alpha = 0.12f)
                .compositeOver(BulkaPediaTheme.colors.primaryDark),
            disabledContentColor = BulkaPediaTheme.colors.primary
                .copy(alpha = ContentAlpha.disabled)
        )
    ) {
        Text(text)
    }
}

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = BulkaPediaTheme.colors.primaryDark,
    text: Int
) {
    TextButton(
        onClick = onClick,
        text = stringResource(text),
        modifier = modifier,
        color = color,
    )
}