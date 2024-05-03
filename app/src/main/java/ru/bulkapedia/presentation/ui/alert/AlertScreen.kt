package ru.bulkapedia.presentation.ui.alert

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WithAlert(message: String?, onClose: () -> Unit) {
    AnimatedVisibility(visible = message != null) {
        AlertDialog(
            onDismissRequest = {/* Ignore on click outside! */},
            confirmButton = {
                Button(onClick = onClose) {
                    Text(text = "Закрыть")
                }
            },
            text = { Text(text = message!!) }
        )
    }
}