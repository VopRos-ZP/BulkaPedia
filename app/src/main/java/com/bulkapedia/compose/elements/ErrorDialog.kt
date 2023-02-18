@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun ErrorDialog(action: ErrorAction) {
    AlertDialog(
        onDismissRequest = {/* On touch outside */},
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        text = { Text(text = action.text.value, color = Teal) },
        title = { Text(text = "Ошибка", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
        modifier = Modifier.border(2.dp, Teal200, RoundedCornerShape(20.dp)),
        confirmButton = {
            InRowOutlinedButton(text = "Закрыть", marginEnd = 10.dp, color = Color.Red) {
                action.show.value = false
                action.onClose.value.invoke()
            }
        }
    )
}
