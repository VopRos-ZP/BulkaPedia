package ru.bulkapedia.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val screen: Screen,
    val title: String,
    val image: ImageVector,
) {
    CATEGORIES(Screen.Wiki, "Вики", Icons.AutoMirrored.Filled.MenuBook),
    AUTH(Screen.Auth, "Авторизация", Icons.AutoMirrored.Filled.Login)
}
