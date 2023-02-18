@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.R
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.profile.ProfileScreen
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.elements.*
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(ctx: ToolbarCtx, viewModel: RegistrationViewModel) {
    ctx.observeAsState()
    ctx.setData("Регистрация", true)
    // UI
    val viewState = viewModel.liveData.observeAsState()
    ScreenWithError { action ->
        when (val state = viewState.value!!) {
            RegistrationViewState.Loading -> RegistrationForm(viewModel, ctx)
            is RegistrationViewState.Success -> ProfileScreen(ctx, state.user, hiltViewModel())
            is RegistrationViewState.Error -> action.showError(state.message) {
                viewModel.obtainEvent(RegistrationEvent.EnterScreen)
            }
        }
    }
    LaunchedEffect(null) {
        viewModel.obtainEvent(RegistrationEvent.EnterScreen)
    }
}

@Composable
fun RegistrationForm(viewModel: RegistrationViewModel, ctx: ToolbarCtx) {
    // store
    val store = DataStore(LocalContext.current)
    val scope = rememberCoroutineScope()
    // state variables
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var visiblePassword by remember { mutableStateOf(false) }
    val confirmPassword = remember { mutableStateOf("") }
    var visibleCPassword by remember { mutableStateOf(false) }
    val nickname = remember { mutableStateOf("") }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                trailingIcon = {
                    val icon = if (visiblePassword) R.drawable.password_visibility_off
                    else R.drawable.password_visibility
                    Image(painter = painterResource(id = icon),
                        colorFilter = ColorFilter.tint(Teal),
                        contentDescription = "password_visibility",
                        modifier = Modifier.clickable {
                            visiblePassword = visiblePassword.not()
                        }
                    )
                },
                visualTransformation = if (visiblePassword) VisualTransformation.None
                else PasswordVisualTransformation(),
            )
            OutlinedTextField(
                text = confirmPassword,
                label = "Подтвердите пароль",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                trailingIcon = {
                    val icon = if (visibleCPassword) R.drawable.password_visibility_off
                    else R.drawable.password_visibility
                    Image(painter = painterResource(id = icon),
                        colorFilter = ColorFilter.tint(Teal),
                        contentDescription = "password_visibility",
                        modifier = Modifier.clickable { visibleCPassword = visibleCPassword.not() }
                    )
                },
                visualTransformation = if (visibleCPassword) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            OutlinedTextField(
                text = nickname,
                label = "Введите ник",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }
        LoginBlock {
            OutlinedButton(text = "Зарегистрироваться") {
                viewModel.obtainEvent(
                    RegistrationEvent.OnSignUpClick(email.value, password.value, nickname.value) {
                        scope.launch {
                            store.saveEmail(email.value)
                            store.saveNickname(nickname.value)
                            store.saveSign(true)
                        }
                    }
                )
            }
            HCenteredBox (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Забыли пароль?",
                    color = Blue,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable(onClick = { ctx.navController.navigate(Destinations.FORGOT_PASSWORD) })
                )
            }
        }
    }
}
