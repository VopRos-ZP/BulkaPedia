package com.bulkapedia.compose.screens.mechanics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bulkapedia.mechanics.Mechanic
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.HCenteredBox

@Composable
fun MechanicsItem(mechanic: Mechanic, onClick: () -> Unit) {
    OutlinedCard(onClick = onClick) {
        HCenteredBox(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(10.dp)
        ) {
            Text(
                text = mechanic.title, color = Teal200,
                fontSize = 16.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}