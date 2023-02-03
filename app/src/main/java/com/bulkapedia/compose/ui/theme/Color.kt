package com.bulkapedia.compose.ui.theme

import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF1C2E46)
val PrimaryDark = Color(0xFF0E131E)
val Purple500 = Color(0xFF6200EE)
val Teal200 = Color(0xFF03DAC5)
val Teal = Color(0xFF00786E)
val Blue = Color(0xFF0057FF)

data class BulkaPediaColors(
    val primary: Color,
    val primaryDark: Color,
    val secondary: Color,
    val secondaryDark: Color,
    val tintColor: Color
)

val basePalette = BulkaPediaColors(
    primary = Primary,
    primaryDark = PrimaryDark,
    secondary = Teal200,
    secondaryDark = Teal,
    tintColor = Teal
)


