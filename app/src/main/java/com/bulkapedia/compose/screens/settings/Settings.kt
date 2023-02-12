@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.bulkapedia.compose.ADMIN_NICKNAME
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.DataStore
import com.bulkapedia.R
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.classes.ChangeValue
import com.bulkapedia.compose.data.classes.Value
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.data.toYearDate
import com.bulkapedia.compose.data.yearFormat
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.elements.OutlinedButton
import com.bulkapedia.compose.elements.ScreenWithChangeDialog
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

@Composable
fun SettingsScreen(ctx: ToolbarCtx, viewModel: SettingsViewModel) {
    // init Toolbar
    ctx.observeAsState()
    ctx.setData(title = "Настройки", showBackButton = true, isVisibleSettings = false)
    // store
    val context = LocalContext.current
    val store = DataStore(context)
    val nickname = store.getNickname.collectAsState(initial = "")
    val signStore = store.getSign.collectAsState(initial = false)
    val scope = rememberCoroutineScope()
    // user
    val viewState = viewModel.liveData.observeAsState()
    // Change value dialog arguments
    val showChange = remember { mutableStateOf(false) }
    val changeFieldLabel = remember { mutableStateOf("") }
    val changeTitle = remember { mutableStateOf("") }
    val changeValue = remember { mutableStateOf(Value.TextValue(mutableStateOf(""))) }
    val changeInfoText = remember { mutableStateOf("") }
    val changeOnSave = remember { mutableStateOf<(Value) -> Unit>({}) }
    val change = ChangeValue(
        showChange, changeTitle, changeFieldLabel, changeInfoText,
        changeValue.value, changeOnSave
    )
    // UI
    // перейти потом на LazyColumn!!! Когда блоков будет много
    TextSnackbar { action ->
        ScreenWithChangeDialog(change) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary)
            ) {
                SettingBlock(title = stringResource(id = R.string.account)) {
                    if (nickname.value == ADMIN_NICKNAME && signStore.value == true) {
                        OutlinedButton(
                            text = "Доска управления",
                            marginStart = 40.dp,
                            marginEnd = 40.dp,
                            marginBottom = 0.dp,
                            enabled = nickname.value == ADMIN_NICKNAME,
                            color = Color.Yellow
                        ) { ctx.navController.navigate(Destinations.DASHBOARD) }
                    }
                    OutlinedButton(
                        text = "Поменять почту",
                        marginStart = 40.dp,
                        marginEnd = 40.dp,
                        marginBottom = 0.dp,
                        color = Color.Yellow
                    ) {
                        if (signStore.value == true) {
                            val db = Database()
                            val state = viewState.value!!
                            if (state is SettingsViewState.Enter) {
                                val user = state.user
                                /* last update email */
                                val lastUpdate = user.updateEmail.toYearDate()
                                val period = Period.between(lastUpdate, LocalDate.now()).toTotalMonths()
                                if (period >= 2) {
                                    changeInfoText.value = "Последущая смена почты будет возможна только через 2 месяца"
                                    changeOnSave.value = { newEmail ->
                                        db.updateEmail(user.email, (newEmail as Value.TextValue).v.value) { newUser ->
                                            scope.launch { store.saveEmail(newUser.email) }
                                            db.addSetsSnapshotListener { sets ->
                                                sets.filter { it.userLikeIds.contains(user.email) }.forEach { set ->
                                                    set.userLikeIds.remove(user.email)
                                                    set.userLikeIds.add(newUser.email)
                                                    db.updateSet(set)
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    val newDate = lastUpdate.plusMonths(2 - period).format(yearFormat)
                                    changeInfoText.value = "Вы уже сменили почту ${lastUpdate.format(yearFormat)}, " +
                                            "смена будет доступна: " + newDate
                                    changeOnSave.value = {}
                                }
                                changeTitle.value = "Изменить почту"
                                changeValue.value = Value.TextValue(mutableStateOf(user.email))
                                changeFieldLabel.value = "Почта"
                                showChange.value = true
                            }
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
                        if (signStore.value == true) {
                            val db = Database()
                            val state = viewState.value!!
                            if (state is SettingsViewState.Enter) {
                                val user = state.user
                                /* last update nickname */
                                val lastUpdate = user.updateNickname.toYearDate()
                                val period = Period.between(lastUpdate, LocalDate.now()).toTotalMonths()
                                if (period >= 2) {
                                    changeInfoText.value = "Последущая смена ника будет возможна только через 2 месяца"
                                    changeOnSave.value = { newNick ->
                                        db.updateNickname(user.email, (newNick as Value.TextValue).v.value) { newUser ->
                                            scope.launch { store.saveNickname(newUser.nickname) }
                                            db.addSetsSnapshotListener { sets ->
                                                sets.filter { it.from == nickname.value }.forEach { set ->
                                                    set.from = newUser.nickname
                                                    db.updateSet(set)
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    val newDate = lastUpdate.plusMonths(2 - period).format(yearFormat)
                                    changeInfoText.value = "Вы уже сменили ник ${lastUpdate.format(yearFormat)}, " +
                                            "смена будет доступна: " + newDate
                                    changeOnSave.value = {}
                                }
                                changeTitle.value = "Изменить ник"
                                changeValue.value = Value.TextValue(mutableStateOf(user.nickname))
                                changeFieldLabel.value = "Ник"
                                showChange.value = true
                            }
                        } else {
                            scope.launch {
                                action.showSnackbar("Вы должны войти в аккаунт!")
                            }
                        }
                        /** Изменить бэкграунд `Snackbar` и цвет текста **/
                    }
                    OutlinedButton(
                        text = "Выход",
                        enabled = signStore.value == true,
                        marginStart = 40.dp,
                        marginEnd = 40.dp,
                        color = Color.Red
                    ) {
                        Database().logout()
                        scope.launch { store.saveSign(false) }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
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
                            modifier = Modifier.size(55.dp)
                                .background(Color.Transparent, CircleShape)
                                .border(1.dp, Teal200, CircleShape)
                                .clip(CircleShape)
                                .padding(10.dp)
                                .clickable {
                                    if (signStore.value == false) {
                                        scope.launch {
                                            action.showSnackbar("Вы должны войти в аккаунт!")
                                        }
                                        return@clickable
                                    }
                                    if (nickname.value != ADMIN_NICKNAME && nickname.value != null) {
                                        ctx.navController.navigate("${Destinations.DEV_CHAT}/${nickname.value}/$ADMIN_NICKNAME")
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
    LaunchedEffect(null) {
        viewModel.fetchUser(nickname.value ?: "")
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