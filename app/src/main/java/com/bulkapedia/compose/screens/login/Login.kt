@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.R
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.DataStore
import kotlinx.coroutines.launch
import com.bulkapedia.compose.data.*
import com.bulkapedia.compose.elements.*

@Composable
fun LoginNav(toolbarCtx: ToolbarCtx) {
    Navigation(startDestination = Destinations.SING_IN, toolbarCtx = toolbarCtx,
        screens = listOf(
            ToSETTINGS, ToFORGOT_PASSWORD,
            ToSIGN_IN, ToSIGN_UP,
            ToPROFILE, ToVISIT
        )
    )
}

@Composable
fun Login(ctx: ToolbarCtx, viewModel: LoginViewModel) {
    // init toolbar
    ctx.observeAsState()
    ctx.setTitle("Вход")
    // get stored data
    val store = DataStore(LocalContext.current)
    val scope = rememberCoroutineScope()
    // init view
    val viewState = viewModel.loginLiveData.observeAsState()
    ScreenWithError { action ->
        when (val state = viewState.value) {
            is LoginViewState.Error -> action.showError(state.error) {
                viewModel.obtainEvent(LoginEvent.EnterScreen)
            }
            else -> LoginPage(
                onLoginClick = { email, password ->
                    viewModel.obtainEvent(LoginEvent.OnSignInClick(email, password) { u ->
                        ctx.navController.navigate("${Destinations.PROFILE}/${u.email}")
                        scope.launch {
                            store.saveEmail(u.email)
                            store.saveSign(true)
                            store.saveNickname(u.nickname)
                        }
                    })
                },
                onRegistrationClick = { ctx.navController.navigate(Destinations.SING_UP) },
                onForgotPasswordClick = { ctx.navController.navigate(Destinations.FORGOT_PASSWORD) }
            )
        }
    }
    LaunchedEffect(null) {
        viewModel.obtainEvent(LoginEvent.EnterScreen)
    }
}

@Composable
fun LoginPage(
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onRegistrationClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    // state variables
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var visiblePassword by remember { mutableStateOf(false) }
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
            OutlinedTextField(
                text = password,
                label = stringResource(id = R.string.hint_password),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    val icon = if (visiblePassword) R.drawable.password_visibility_off
                    else R.drawable.password_visibility
                    Image(painter = painterResource(id = icon),
                        colorFilter = ColorFilter.tint(Teal),
                        contentDescription = "password_visibility",
                        modifier = Modifier.clickable { visiblePassword = visiblePassword.not() }
                    )
                },
                visualTransformation = if (visiblePassword) VisualTransformation.None
                else PasswordVisualTransformation(),
            )
        }
        LoginBlock {
            OutlinedButton(text = "Войти") {
                onLoginClick.invoke(email.value, password.value)
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


