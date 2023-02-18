@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bulkapedia.compose.data.User
import com.bulkapedia.compose.data.User.Companion.toUser
import com.bulkapedia.compose.elements.ScreenWithDelete
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.CustomIndicator
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UsersSetsScreen(ctx: ToolbarCtx, viewModel: UsersSetsViewModel) {
    ctx.observeAsState()
    ctx.setData(title = "Пользователи и сеты", showBackButton = true)

    val tabs = listOf("Пользователи", "Сеты")
    /** States **/
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val usersState = viewModel.usersData.observeAsState()
    val setsState = viewModel.setsData.observeAsState()
    // if change main data
    val userState = remember { mutableStateOf<User?>(null) }
    val showAddMainDialog = remember { mutableStateOf(false) }
    val defHero = remember { mutableStateOf("Арни") }
    val defKills = remember { mutableStateOf("10000") }
    val defWR = remember { mutableStateOf("50") }
    val defRevives = remember { mutableStateOf("0") }
    // Delete
    val showDelete = remember { mutableStateOf(false) }
    val whatDelete = remember { mutableStateOf("сет") }
    val deleteAction = remember { mutableStateOf({}) }
    // Search

    // UI
    ScreenWithDelete(
        show = showDelete,
        whatDelete = whatDelete.value,
        onDelete = { deleteAction.value.invoke() }
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 20.dp)
                .background(Primary)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .height(50.dp),
                elevation = 10.dp,
                shape = RoundedCornerShape(50.dp),
                backgroundColor = PrimaryDark,
                border = BorderStroke(2.dp, Teal200)
            ) {
                TabRow(
                    modifier = Modifier.fillMaxHeight(),
                    selectedTabIndex = pagerState.currentPage,
                    indicator = @Composable {
                        CustomIndicator(
                            tabPositions = it,
                            pagerState = pagerState
                        )
                    },
                    contentColor = PrimaryDark,
                    backgroundColor = PrimaryDark
                ) {
                    tabs.mapIndexed { i, tabItem ->
                        Tab(
                            modifier = Modifier.zIndex(2f),
                            selected = pagerState.currentPage == i,
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(i) }
                            },
                            selectedContentColor = PrimaryDark,
                            unselectedContentColor = PrimaryDark
                        ) {
                            Text(
                                tabItem,
                                color = if (pagerState.currentPage == i) PrimaryDark
                                else Teal
                            )
                        }
                    }
                }
            }
            CenteredBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.923f)
                    .padding(horizontal = 20.dp)
                    .background(PrimaryDark, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
            ) {
                HorizontalPager(
                    count = tabs.size,
                    state = pagerState
                ) { page ->
                    when (page) {
                        0 -> UsersRecycler(
                            usersState.value ?: emptyList(), userState,
                            showAddMainDialog, defHero, defKills, defWR, defRevives
                        ) { hero, user ->
                            showDelete.value = true
                            whatDelete.value = "мейн героя"
                            deleteAction.value = {
                                val db = Firebase.database.reference.child("users")
                                db.addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        for (ch in snapshot.children) {
                                            ch.toUser()?.let { u ->
                                                if (u.email == user.email && u.nickname == user.nickname) {
                                                    Firebase.database.reference.child("users")
                                                        .child(ch.key!!).child("mains")
                                                        .child(hero).removeValue()
                                                }
                                            }
                                        }
                                    }
                                    override fun onCancelled(error: DatabaseError) {}
                                })
                            }
                        }
                        1 -> SetsRecycler(setsState.value ?: emptyList()) { s ->
                            showDelete.value = true
                            whatDelete.value = "сет"
                            deleteAction.value = {
                                val fs = Firebase.firestore
                                fs.collection("sets").document(s.userSetId).delete().addOnSuccessListener {
                                    fs.collection("comments").whereEqualTo("set", s.userSetId)
                                        .get().addOnSuccessListener { q ->
                                            q.documents.forEach { d -> d.reference.delete() }
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    DisposableEffect(null) {
        viewModel.listenUsers()
        viewModel.listenSets()
        onDispose { viewModel.removeListeners() }
    }
}