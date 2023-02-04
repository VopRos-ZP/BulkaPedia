@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal

@Composable
fun ErrorDialog(
    msg: String,
    state: MutableState<Boolean>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {/* On touch outside */},
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        text = { Text(text = msg, color = Teal) },
        title = { Text(text = "Ошибка", color = Color.Red, fontSize = 16.sp) },
        confirmButton = {
            OutlinedButton(
                onClick = { state.value = false; onDismiss.invoke() },
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(2.dp, Color.Red)
            ) {
                Text(text = "Закрыть", color = Color.Red)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDialog() {
    val show = remember { mutableStateOf(true) }
    ErrorDialog("Пользователь не найден", show) {}
}