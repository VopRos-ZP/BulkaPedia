package ru.bulkapedia.presentation.ui.screens.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.bulkapedia.presentation.ui.components.CenterContent
import ru.bulkapedia.presentation.ui.screens.Login
import ru.bulkapedia.presentation.ui.screens.Registration
import ru.bulkapedia.presentation.ui.screens.login.LoginScreen
import ru.bulkapedia.presentation.ui.screens.registration.RegistrationScreen

@Composable
fun AuthScreen() {
    val navController = rememberNavController()
    CenterContent(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = Registration
        ) {
            composable<Login> {
                LoginScreen(

                )
            }
            composable<Registration> {
                RegistrationScreen(

                )
            }
        }
    }
}
