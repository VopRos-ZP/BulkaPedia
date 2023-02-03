@file:Suppress("FunctionName")

package com.bulkapedia.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.data.sets.UserSet

@Composable
fun ConfirmDialog(
    state: MutableState<Boolean>,
    title: String,
    text: String,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {/* On touch outside */},
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        title = { Text(text = title, color = Teal200, fontWeight = FontWeight.Bold) },
        text = { Text(text = text, color = Teal200) },
        buttons = {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { state.value = false },
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                    border = BorderStroke(2.dp, Color.Red)
                ) {
                    Text(text = "Отмена", color = Color.Red)
                }
                OutlinedButton(
                    onClick = { state.value = false; onConfirm.invoke() },
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                    border = BorderStroke(2.dp, Color.Yellow)
                ) {
                    Text(text = "Удалить", color = Color.Yellow)
                }
            }
        }
    )
}