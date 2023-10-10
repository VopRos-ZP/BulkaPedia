package vopros.bulkapedia.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.button.OutlinedButton
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.ui.theme.BulkapediaTheme

@Preview(showBackground = true)
@Composable
fun Error_Preview() {
    BulkapediaTheme {
        Error("Registration error") {}
    }
}

@Composable
fun Error(message: String, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /* on click outside dialog */ },
        text = { Text(message, color = Color.Red) },
        confirmButton = {
            OutlinedButton(onClick = onClose, text = R.string.ok)
        },
        backgroundColor = BulkaPediaTheme.colors.primaryDark,
        contentColor = BulkaPediaTheme.colors.primary
    )
}