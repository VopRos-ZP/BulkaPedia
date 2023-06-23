package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.elements.ITabRow
import com.bulkapedia.compose.elements.ScreenWithDelete
import com.bulkapedia.compose.elements.ScreenWithTagDialog
import com.bulkapedia.compose.elements.SearchView
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersSetsScreen(viewModel: UsersSetsViewModel = hiltViewModel()) {
    val tabs = listOf("Пользователи", "Сеты")
    /** States **/
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val users by viewModel.usersFlow.collectAsState()
    val sets by viewModel.setsFlow.collectAsState()
    // Search
    val searchText = remember { mutableStateOf("") }
    ScreenView(title = "Пользователи и сеты", showBack = true) {
        ScreenWithDelete { action ->
            ScreenWithTagDialog { tagAction ->
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 20.dp)
                        .background(Primary)
                ) {
                    SearchView(searchText)
                    ITabRow(pagerState = pagerState) {
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
                    HorizontalPager(
                        pageCount = tabs.size,
                        state = pagerState
                    ) { page ->
                        when (page) {
                            0 -> UsersRecycler(
                                filterUsersList(searchText, users), tagAction
                            ) { hero, user -> action.showDelete("мейн героя") {
                                viewModel.removeMainsHero(user, hero)
                            } }
                            1 -> SetsRecycler(filterSetsList(searchText, sets)) { s ->
                                action.showDelete("сет") { viewModel.removeUserSet(s) }
                            }
                        }
                    }
                }
            }
        }
    }
    DisposableEffect(null) {
        viewModel.fetchData()
        onDispose { viewModel.removeListeners() }
    }
}

private fun filterUsersList(searchState: MutableState<String>, list: List<User>): List<User> {
    val text = searchState.value
    return if (text.isEmpty()) list
    else {
        list.filter { user ->
            user.email.contains(text) || user.nickname.contains(text)
        }
    }
}

private fun filterSetsList(searchState: MutableState<String>, list: List<UserSet>): List<UserSet> {
    val text = searchState.value
    return if (text.isEmpty()) list
    else {
        list.filter { set ->
            set.from.contains(text) || set.hero.contains(text)
        }
    }
}