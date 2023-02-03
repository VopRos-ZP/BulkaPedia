@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.settings

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.elements.OutlinedButton
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(ctx: ToolbarCtx) {
    // init Toolbar
    ctx.observeAsState()
    ctx.setData(title = "Настройки", showBackButton = true, isVisibleSettings = false)
    // store
    val context = LocalContext.current
    val store = DataStore(context)
    val nickname = store.getNickname.collectAsState(initial = "")
    val signStore = store.getSign.collectAsState(initial = false)
    val scope = rememberCoroutineScope()
    // UI
    // перейти потом на LazyColumn!!! Когда блоков будет много
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        SettingBlock(title = stringResource(id = R.string.account)) {
            if (nickname.value == ADMIN_NICKNAME) {
                OutlinedButton(
                    text = "Доска управления",
                    marginStart = 40.dp,
                    marginEnd = 40.dp,
                    enabled = nickname.value == ADMIN_NICKNAME,
                    color = Color.Yellow
                ) { ctx.navController.navigate(Destinations.DASHBOARD) }
            }
            OutlinedButton(
                text = "Поменять ник",
                enabled = signStore.value == true,
                marginStart = 40.dp,
                marginEnd = 40.dp,
                color = Color.Yellow
            ) { /* change nickname dialog */ }
            OutlinedButton(
                text = "Выход",
                enabled = signStore.value == true,
                marginStart = 40.dp,
                marginEnd = 40.dp,
                color = Color.Red
            ) { scope.launch { store.saveSign(false) } }
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
                                Toast.makeText(context,
                                    "Только зарегистрированные пользователи имеют доступ к этому чату",
                                    Toast.LENGTH_SHORT).show()
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