package ru.bulkapedia.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import ru.bulkapedia.presentation.navigation.Screen

@Composable
fun SplashScreen(onNavigate: (Screen) -> Unit) {
    LaunchedEffect(Unit) {
        //delay(3000)
        onNavigate(Screen.Root)
    }
}