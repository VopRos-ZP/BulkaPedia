package com.bulkapedia.compose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BulkaPediaShapes (
    val round10: Shape,
    val round15: Shape,
    val round20: Shape,
    val round25: Shape,
    val round30: Shape,
    val round35: Shape,
    val round40: Shape,
    val round45: Shape,
    val round50: Shape
)

val roundedShapes = BulkaPediaShapes(
    round10 = RoundedCornerShape(10.dp),
    round15 = RoundedCornerShape(15.dp),
    round20 = RoundedCornerShape(20.dp),
    round25 = RoundedCornerShape(25.dp),
    round30 = RoundedCornerShape(30.dp),
    round35 = RoundedCornerShape(35.dp),
    round40 = RoundedCornerShape(40.dp),
    round45 = RoundedCornerShape(45.dp),
    round50 = RoundedCornerShape(50.dp)
)

