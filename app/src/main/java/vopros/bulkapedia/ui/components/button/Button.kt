package vopros.bulkapedia.ui.components.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: Int
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BulkaPediaTheme.colors.primaryDark,
            disabledBackgroundColor = BulkaPediaTheme.colors.primaryDark.copy(alpha = 0.12f)
                .compositeOver(BulkaPediaTheme.colors.primaryDark),
            disabledContentColor = BulkaPediaTheme.colors.primary
                .copy(alpha = ContentAlpha.disabled)
        )
    ) {
        Text(resource = text)
    }
}