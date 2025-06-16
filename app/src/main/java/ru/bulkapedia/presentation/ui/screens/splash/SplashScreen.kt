package ru.bulkapedia.presentation.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onFinish: () -> Unit,
) {
    viewModel.collectSideEffect {
        when (it) {
            is SplashSideEffect.OnFinish -> onFinish()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}