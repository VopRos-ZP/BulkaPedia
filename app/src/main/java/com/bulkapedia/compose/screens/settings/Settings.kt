package com.bulkapedia.compose.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.ADMIN_NICKNAME
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.DataStore
import com.bulkapedia.R
import com.bulkapedia.compose.data.classes.ChangeValue
import com.bulkapedia.compose.data.classes.Value
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.elements.OutlinedButton
import com.bulkapedia.compose.elements.ScreenWithChangeDialog
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    // store
    val context = LocalContext.current
    val store = DataStore(context)
    val nickname by store.getNickname.collectAsState("")
    val email by store.getEmail.collectAsState("")
    val signStore by store.getSign.collectAsState(false)
    val scope = rememberCoroutineScope()
    // Change value dialog arguments
    val showChange = remember { mutableStateOf(false) }
    val changeFieldLabel = remember { mutableStateOf("") }
    val changeTitle = remember { mutableStateOf("") }
    val changeValue = remember { mutableStateOf(Value.TextValue(mutableStateOf(""))) }
    val changeInfoText = remember { mutableStateOf("") }
    val changeOnSave = remember { mutableStateOf<(Value) -> Unit>({}) }
    val change = remember { mutableStateOf(ChangeValue(
        showChange, changeTitle, changeFieldLabel, changeInfoText,
        changeValue.value, changeOnSave
    )) }

    val user = viewModel.user[0]
    ScreenView(title = "Настройки", showBack = true) {
        // перейти потом на LazyColumn!!! Когда блоков будет много
        TextSnackbar { action ->
            ScreenWithChangeDialog(change.value) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Primary)
                ) {
                    SettingBlock(title = stringResource(id = R.string.account)) {
                        if (nickname == ADMIN_NICKNAME && signStore) {
                            OutlinedButton(
                                text = "Доска управления",
                                marginStart = 40.dp,
                                marginEnd = 40.dp,
                                marginBottom = 0.dp,
                                enabled = true,
                                color = Color.Yellow
                            ) { navController.navigate(Destinations.DASHBOARD) }
                        }
                        OutlinedButton(
                            text = "Поменять почту",
                            marginStart = 40.dp,
                            marginEnd = 40.dp,
                            marginBottom = 0.dp,
                            color = Color.Yellow
                        ) {
                            if (signStore && user != null) {
                                change.value = viewModel.changeEmail(user, change.value) {
                                    store.saveEmail(it.email)
                                }
                                showChange.value = true
                            } else {
                                scope.launch {
                                    action.showSnackbar("Вы должны войти в аккаунт!")
                                }
                            }
                        }
                        OutlinedButton(
                            text = "Поменять ник",
                            marginStart = 40.dp,
                            marginEnd = 40.dp,
                            marginBottom = 0.dp,
                            color = Color.Yellow
                        ) {
                            if (signStore && user != null) {
                                change.value = viewModel.changeNickname(user, change.value) {
                                    store.saveNickname(it.nickname)
                                }
                                showChange.value = true
                            } else {
                                scope.launch {
                                    action.showSnackbar("Вы должны войти в аккаунт!")
                                }
                            }
                            /** Изменить бэкграунд `Snackbar` и цвет текста **/
                        }
                        OutlinedButton(
                            text = "Выход",
                            enabled = signStore,
                            marginStart = 40.dp,
                            marginEnd = 40.dp,
                            color = Color.Red
                        ) { viewModel.logout { store.saveSign(false) } }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp, top = 10.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.comment),
                                contentDescription = "связь с разраотчиком",
                                colorFilter = ColorFilter.tint(Teal200),
                                modifier = Modifier
                                    .size(55.dp)
                                    .background(Color.Transparent, CircleShape)
                                    .border(1.dp, Teal200, CircleShape)
                                    .clip(CircleShape)
                                    .padding(10.dp)
                                    .clickable {
                                        if (!signStore) {
                                            scope.launch {
                                                action.showSnackbar("Вы должны войти в аккаунт!")
                                            }
                                            return@clickable
                                        }
                                        if (nickname != ADMIN_NICKNAME && nickname.isNotEmpty()) {
                                            navController.navigate("${Destinations.DEV_CHAT}/$nickname/$ADMIN_NICKNAME")
                                        }
                                    }
                            )
                            Text(
                                "Связь с\nразработчиком",
                                color = Teal200,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
    DisposableEffect(email) {
        viewModel.fetchUser(email)
        onDispose { viewModel.dispose() }
    }
}

@Composable
private fun SettingBlock(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HCenteredBox (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Teal200,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}