@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.mechanics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.data.category.Mechanic
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable

@Composable
fun MechanicsItem(mechanic: Mechanic, onClick: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(2.dp, Teal200),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        HCenteredBox(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable { onClick.invoke(mechanic.id) }
        ) {
            Text(
                text = mechanic.title, color = Teal200,
                fontSize = 16.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}