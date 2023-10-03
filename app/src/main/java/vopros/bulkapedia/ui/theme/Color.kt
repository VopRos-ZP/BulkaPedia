package vopros.bulkapedia.ui.theme

import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF2D2D30)
val Secondary = Color(0xFF1E1F22)
val White = Color(0xFFDFE1E5)
val SecondaryAscent = Color(0xFF43454A)
val Teal200 = Color(0xFF03DAC5)
val Blue = Color(0xFF366ACE)

data class BulkaPediaColors(
    val primary: Color,
    val primaryDark: Color,
    val secondary: Color,
    val secondaryDark: Color,
    val tintColor: Color,
    val white: Color
)

val basePalette = BulkaPediaColors(
    primary = Primary,
    primaryDark = Secondary,
    secondary = SecondaryAscent,
    secondaryDark = Teal200,
    tintColor = Blue,
    white = White
)