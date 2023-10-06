package vopros.bulkapedia.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun Loading() {
    CenterBox { CircularProgressIndicator(color = BulkaPediaTheme.colors.tintColor) }
}