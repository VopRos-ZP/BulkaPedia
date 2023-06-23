package com.bulkapedia.compose.screens.passwordreset

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.R
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import kotlinx.coroutines.launch

@Composable
fun PasswordResetScreen(viewModel: PasswordResetViewModel = hiltViewModel()) {
    val emailCheck by viewModel.emailCheckFlow.collectAsState()
    val email by viewModel.emailFlow.collectAsState()
    val error by viewModel.errorFlow.collectAsState()
    ScreenView(title = "Восстановить пароль", showBack = true) {
        ScreenWithError { err ->
            when {
                error != null -> err.showError(error!!) {  }
                else -> PasswordResetForm(viewModel, email, emailCheck)
            }
        }
    }
}

@Composable
private fun PasswordResetForm(
    viewModel: PasswordResetViewModel,
    emailT: String = "",
    isEmailChecked: Boolean = false
) {
    val navController = LocalNavController.current
    // store
    val store = DataStore(LocalContext.current)
    val scope = rememberCoroutineScope()
    // state variables
    val email = remember { mutableStateOf(emailT) }
    val password = remember { mutableStateOf("") }
    // UI
    Column(modifier = Modifier.fillMaxSize()) {
        FirstLoginBlock {
            OutlinedTextField(
                text = email,
                readOnly = isEmailChecked,
                label = stringResource(id = R.string.enter_email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            if (isEmailChecked) {
                OutlinedPasswordField(password)
            }
        }
        LoginBlock {
            if (isEmailChecked) {
                AgreeInfoBox("На указанную почту отправлено письмо с ссылкой на сброс пароля")
            }
            OutlinedButton(text = if (isEmailChecked) "Войти" else "Отправить письмо") {
                if (isEmailChecked) {
                    if (password.value.isEmpty()) return@OutlinedButton
                    viewModel.checkPassword(email.value, password.value) {
                        navController.navigate("${Destinations.PROFILE}/${it.email}")
                        store.saveSign(true)
                        store.saveEmail(it.email)
                        store.saveNickname(it.nickname)
                    }
                } else {
                    if (email.value.isEmpty()) return@OutlinedButton
                    viewModel.checkEmail(email.value)
                }
            }
        }
    }
}
