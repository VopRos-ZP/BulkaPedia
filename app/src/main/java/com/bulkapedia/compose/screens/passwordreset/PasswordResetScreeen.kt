@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.passwordreset

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
import com.bulkapedia.R
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.User
import com.bulkapedia.compose.DataStore
import kotlinx.coroutines.launch

@Composable
fun PasswordResetScreen(ctx: ToolbarCtx, viewModel: PasswordResetViewModel) {
    ctx.observeAsState()
    ctx.setData(title = "Восстановить пароль", showBackButton = true)
    // ViewState
    val viewState = viewModel.liveData.observeAsState()
    val showErrorDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }
    // UI
    ScreenWithError(
        showErrorDialog,
        text = errorMessage.value,
        onClose = { viewModel.obtainEvent(PasswordResetEvent.NoEvent) }
    ) {
        when (val state = viewState.value!!) {
            is PasswordResetViewState.Error -> {
                showErrorDialog.value = true
                errorMessage.value = state.message
            }
            is PasswordResetViewState.CheckedEmail -> PasswordResetForm(ctx, viewModel, state.email, true)
            else -> PasswordResetForm(ctx, viewModel)
        }
    }
    LaunchedEffect(null) {
        viewModel.obtainEvent(PasswordResetEvent.NoEvent)
    }
}

@Composable
private fun PasswordResetForm(
    ctx: ToolbarCtx,
    viewModel: PasswordResetViewModel,
    emailT: String = "",
    isEmailChecked: Boolean = false
) {
    // store
    val store = DataStore(LocalContext.current)
    val scope = rememberCoroutineScope()
    // state variables
    val email = remember { mutableStateOf(emailT) }
    val password = remember { mutableStateOf("") }
    var visiblePassword by remember { mutableStateOf(false) }
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
                            modifier = Modifier.clickable {
                                visiblePassword = visiblePassword.not()
                            }
                        )
                    },
                    visualTransformation = if (visiblePassword) VisualTransformation.None
                    else PasswordVisualTransformation(),
                )
            }
        }
        LoginBlock {
            if (isEmailChecked) {
                AgreeInfoBox(
                    text = "На указанную почту отправлено письмо с ссылкой на сброс пароля"
                )
            }
            OutlinedButton(text = if (isEmailChecked) "Войти" else "Отправить письмо") {
                if (isEmailChecked) {
                    if (password.value.isEmpty()) return@OutlinedButton
                    val saveDataStore: (User) -> Unit = { u ->
                        ctx.navController.navigate("${Destinations.PROFILE}/${u.email}")
                        scope.launch {
                            store.saveSign(true);
                            store.saveEmail(u.email)
                            store.saveNickname(u.nickname)
                        }
                    }
                    viewModel.obtainEvent(
                        PasswordResetEvent.CheckNewPassword(
                            email.value, password.value, saveDataStore
                        )
                    )
                } else {
                    if (email.value.isEmpty()) return@OutlinedButton
                    viewModel.obtainEvent(PasswordResetEvent.CheckEmail(email.value))
                }
            }
        }
    }
}
