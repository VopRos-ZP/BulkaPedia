package ru.bulkapedia.presentation.ui.theme

import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF2D2D30)
val Secondary = Color(0xFF1E1F22)
val White = Color(0xFFDFE1E5)
val SecondaryAscent = Color(0xFF43454A)
val Teal200 = Color(0xFF03DAC5)
val Blue = Color(0xFF366ACE)

val Common = Color(0xFF526977)
val Rare = Color(0xFF73D451)
val Epic = Color(0xFF03A9F4)
val Legendary = Color(0xFFFCC10E)
val Mythic = Color(0xFFE67110)
val Supreme = Color(0xFFF6412C)
val Ultimate = Color(0xFFC826E8)
val Celestial = Color(0xFF0837C5)
val Stellar = Color(0xFF612EE4)
val Immortal = Color(0xFF73000A)
val Divine = Color(0xFF101430)

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

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)