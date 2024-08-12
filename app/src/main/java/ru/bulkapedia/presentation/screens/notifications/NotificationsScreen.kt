package ru.bulkapedia.presentation.screens.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.bulkapedia.presentation.navigation.Screen

@Composable
fun NotificationsScreen(
    onNavigate: (Screen) -> Unit,
) {
    Box(modifier = Modifier) {
        Text(text = "Уведомления")
    }
}