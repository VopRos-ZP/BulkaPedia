package com.bulkapedia.compose.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.R
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.navigation.ToFORGOT_PASSWORD
import com.bulkapedia.compose.navigation.ToPROFILE
import com.bulkapedia.compose.navigation.ToSETTINGS
import com.bulkapedia.compose.navigation.ToSIGN_IN
import com.bulkapedia.compose.navigation.ToSIGN_UP
import com.bulkapedia.compose.navigation.ToVISIT
import com.bulkapedia.compose.screens.titled.ScreenView
import kotlinx.coroutines.launch

@Composable
fun LoginNav() {
    Navigation(startDestination = Destinations.SING_IN, screens = listOf(
        ToSETTINGS, ToFORGOT_PASSWORD,
        ToSIGN_IN, ToSIGN_UP,
        ToPROFILE, ToVISIT
    ))
}

@Composable
fun Login(viewModel: LoginViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val store = DataStore(LocalContext.current)
    val error by viewModel.errorFlow.collectAsState()
    val scope = rememberCoroutineScope()

    ScreenView("Вход") {
        ScreenWithError { action ->
            if (error != null) { action.showError(error!!) {} }
            LoginPage(
                onLoginClick = { email, password ->
                    viewModel.login(email, password) { u ->
                        navController.navigate("${Destinations.PROFILE}/${u.email}")
                        scope.launch {
                            store.saveEmail(u.email)
                            store.saveSign(true)
                            store.saveNickname(u.nickname)
                        }
                    }
                },
                onRegistrationClick = { navController.navigate(Destinations.SING_UP) },
                onForgotPasswordClick = { navController.navigate(Destinations.FORGOT_PASSWORD) }
            )
        }
    }
}

@Composable
fun LoginPage(
    onLoginClick: (String, String) -> Unit,
    onRegistrationClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    // state variables
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    // UI
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FirstLoginBlock {
            OutlinedTextField(
                text = email,
                label = stringResource(id = R.string.enter_email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            OutlinedPasswordField(password)
        }
        LoginBlock {
            OutlinedButton(text = "Войти") {
                onLoginClick.invoke(email.value.trim(), password.value.trim())
            }
            OutlinedButton(
                text = "Регистрация",
                marginTop = 0.dp,
                onClick = onRegistrationClick
            )
            HCenteredBox (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Забыли пароль?",
                    color = Blue,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable(onClick = onForgotPasswordClick)
                )
            }
        }
    }
}


