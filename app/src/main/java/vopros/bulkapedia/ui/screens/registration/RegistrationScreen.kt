package vopros.bulkapedia.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.OutlinedTextField
import vopros.bulkapedia.ui.components.PasswordField
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.button.OutlinedButton
import vopros.bulkapedia.ui.theme.BulkapediaTheme

@Preview
@Composable
fun RegistrationScreen_Preview() {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val repeatPasswordState = remember { mutableStateOf("") }
    val nicknameState = remember { mutableStateOf("") }
    BulkapediaTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_no_bg),
                contentDescription = "logo"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(state = emailState, label = R.string.enter_email)
                OutlinedTextField(state = nicknameState, label = R.string.hint_nickname)
                PasswordField(state = passwordState, label = R.string.hint_password)
                PasswordField(state = repeatPasswordState, label = R.string.hint_password)
            }
            OutlinedButton(onClick = {}, text = R.string.registration)
        }
    }
}

@Destination
@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val repeatPasswordState = remember { mutableStateOf("") }
    val nicknameState = remember { mutableStateOf("") }
    ScreenView(
        title = R.string.registration,
        showBack = true,
        viewModel = viewModel
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_no_bg),
                contentDescription = "logo"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(state = emailState, label = R.string.enter_email)
                OutlinedTextField(state = nicknameState, label = R.string.hint_nickname)
                PasswordField(state = passwordState, label = R.string.hint_password)
                PasswordField(state = repeatPasswordState, label = R.string.hint_password)
            }
            OutlinedButton(onClick = {
                viewModel.register(emailState.value, passwordState.value, nicknameState.value)
            },
                text = R.string.registration,
                enabled = passwordState.value == repeatPasswordState.value
            )
        }
    }
}