package com.bulkapedia.compose.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.screens.titled.ScreenView
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = hiltViewModel()) {
    val error by viewModel.errorFlow.collectAsState()
    val navController = LocalNavController.current
    ScreenView("Регистрация", true) {
        ScreenWithError { action ->
            when (error) {
                null -> RegistrationForm(viewModel) { user ->
                    navController.navigate("${Destinations.PROFILE}/${user.email}") {
                        launchSingleTop = true
                    }
                }
                else -> action.showError(error!!)
            }
        }
    }
}

@Composable
fun RegistrationForm(viewModel: RegistrationViewModel, onSuccess: (User) -> Unit) {
    // store
    val store = DataStore(LocalContext.current)
    val scope = rememberCoroutineScope()
    // state variables
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val navController = LocalNavController.current
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
            OutlinedPasswordField(password)
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
                viewModel.registration(email.value, password.value, nickname.value) {
                    scope.launch {
                        store.saveEmail(email.value)
                        store.saveNickname(nickname.value)
                        store.saveSign(true)
                    }
                    onSuccess(it)
                }
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
                    modifier = Modifier.clickable(onClick = { navController.navigate(Destinations.FORGOT_PASSWORD) })
                )
            }
        }
    }
}
