@file:Suppress("FunctionName")

package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ADMIN_NICKNAME
import com.bulkapedia.compose.elements.MainTagSettingsMenu
import com.bulkapedia.compose.screens.sets.DarkButton
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.labels.Stats
import com.bulkapedia.compose.data.User
import com.bulkapedia.R
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.ui.theme.LocalToolbarContext

@Composable
fun UsersRecycler(
    users: List<User>,
    userState: MutableState<User?>,
    showDialog: MutableState<Boolean>,
    defHero: MutableState<String>,
    defKills: MutableState<String>,
    defWR: MutableState<String>,
    defRevives: MutableState<String>,
    onDelete: (String, User) -> Unit
) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth()
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .border(2.dp, Teal200, RoundedCornerShape(20.dp))
    ) {
        items(users) {user ->
            UserRecyclerItem(user, userState, showDialog, defHero, defKills, defWR, defRevives, onDelete)
        }
    }
}

@Composable
fun UserRecyclerItem(
    user: User,
    userState: MutableState<User?>,
    showDialog: MutableState<Boolean>,
    defHero: MutableState<String>,
    defKills: MutableState<String>,
    defWR: MutableState<String>,
    defRevives: MutableState<String>,
    onDelete: (String, User) -> Unit
) {
    val nav = LocalToolbarContext.current.navController
    Card (
        backgroundColor = PrimaryDark,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Teal200),
        modifier = Modifier.fillMaxWidth()
            .padding(20.dp)
    ) {
        Column (
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
        ) {
            // email
            UserItemRow {
                Text("Почта: ", color = Teal200)
                Text(
                    text = user.email,
                    color = Teal200
                )
            }
            UserItemRow {
                Text("Пароль: ", color = Teal200)
                Text(
                    text = user.password,
                    color = Teal200
                )
            }
            UserItemRow {
                Text("Ник: ", color = Teal200)
                Text(
                    text = user.nickname,
                    color = Teal200
                )
            }
            UserItemRow {
                DarkButton(R.drawable.comment) {
                    nav.navigate("${Destinations.DEV_CHAT}/$ADMIN_NICKNAME/${user.nickname}")
                }
            }
            UserItemLazyRow {
                item { AddTagView(user, userState, showDialog) }
                user.mains?.map {
                    item {
                        MainTagView(
                            user, userState, it.key, it.value, showDialog,
                            defHero, defKills, defWR, defRevives, onDelete
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserItemRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
        content = content
    )
}

@Composable
fun UserItemLazyRow(content: LazyListScope.() -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
        content = content
    )
}

@Composable
fun AddTagView(
    user: User,
    userState: MutableState<User?>,
    showDialog: MutableState<Boolean>
) {
    TagView(onClick = { userState.value = user; showDialog.value = true }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "add",
            tint = Teal200
        )
    }
}

@Composable
fun MainTagView(
    user: User,
    userState: MutableState<User?>,
    main: String,
    stats: Stats,
    showDialog: MutableState<Boolean>,
    defHero: MutableState<String>,
    defKills: MutableState<String>,
    defWR: MutableState<String>,
    defRevives: MutableState<String>,
    onDelete: (String, User) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    VCenteredBox {
        TagView (onClick = {
            expanded.value = !expanded.value
        }) {
            Text(text = main, color = Teal200)
        }
        if (expanded.value) {
            MainTagSettingsMenu(
                expanded, Pair(main, stats),
                onEditClick = {
                    showDialog.value = true
                    userState.value = user
                    defHero.value = it.first
                    defKills.value = "${it.second.kills}"
                    defWR.value = "${it.second.winrate}"
                    defRevives.value = "${it.second.revives}"
                              },
                onDeleteClick = { onDelete.invoke(main, user) }
            )
        }
    }
}

@Composable
fun TagView(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Box (modifier = Modifier.padding(end = 7.dp)) {
        Row(
            modifier = Modifier
                .background(PrimaryDark, RoundedCornerShape(10.dp))
                .border(1.dp, Teal200, RoundedCornerShape(10.dp))
                .clickable(onClick)
                .padding(5.dp),
            content = content
        )
    }
}