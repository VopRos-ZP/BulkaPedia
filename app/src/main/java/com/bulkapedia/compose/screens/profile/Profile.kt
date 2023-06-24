package com.bulkapedia.compose.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import com.bulkapedia.compose.navigation.NavigationScreen
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.screens.sets.SetInProfileCard
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.labels.Stats
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.navigation.ToCATEGORY_MANAGE
import com.bulkapedia.compose.navigation.ToCOMMENTS
import com.bulkapedia.compose.navigation.ToCREATE_SET
import com.bulkapedia.compose.navigation.ToDASHBOARD
import com.bulkapedia.compose.navigation.ToDEV_CHAT
import com.bulkapedia.compose.navigation.ToMANAGE_HEROES_INFO
import com.bulkapedia.compose.navigation.ToSETTINGS
import com.bulkapedia.compose.navigation.ToSIGN_IN
import com.bulkapedia.compose.navigation.ToUSERS_SETS
import com.bulkapedia.compose.navigation.ToVISIT
import com.bulkapedia.compose.navigation.navArg
import com.bulkapedia.compose.screens.Loading
import com.bulkapedia.compose.screens.titled.ScreenView
import kotlinx.coroutines.launch

@Composable
fun ProfileListNav(startDestination: String, dEmail: String) {
    Navigation(startDestination, screens = listOf(
        NavigationScreen(startDestination,
            listOf(navArg("email", NavType.StringType, dEmail))) { args ->
            Profile(args?.getString("email")!!, hiltViewModel()) }, ToSETTINGS,
            ToSIGN_IN, ToDASHBOARD, ToCOMMENTS,
            ToVISIT, ToCREATE_SET, ToDEV_CHAT,
            ToCATEGORY_MANAGE, ToUSERS_SETS, ToMANAGE_HEROES_INFO,
        )
    )
}

@Composable
fun Profile(email: String, viewModel: ProfileViewModel = hiltViewModel()) {
    val userState = viewModel.userFlow.collectAsState()
    when (val user = userState.value) {
        null -> Loading()
        else -> ProfileScreen(user, viewModel)
    }
    DisposableEffect(null) {
        viewModel.fetchUser { it.email == email }
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun VisitProfileScreen(nickname: String, viewModel: ProfileViewModel) {
    val userState = viewModel.userFlow.collectAsState()
    when (val user = userState.value) {
        null -> Loading()
        else -> ProfileScreen(user, viewModel, true)
    }
    DisposableEffect(null) {
        viewModel.fetchUser { it.nickname == nickname }
        onDispose { viewModel.removeListener() }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(user: User, viewModel: ProfileViewModel, visit: Boolean = false) {
    val setsState = viewModel.userSetsFlow.collectAsState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val showMainStat = remember { mutableStateOf<Stats?>(null) }
    val tabs = if (visit) {
        listOf(ProfileTab(title = "Cеты") { set -> set.from == user.nickname })
    } else {
        listOf(
            ProfileTab(title = "Ваши сеты") { set -> set.from == user.nickname },
            ProfileTab(title = "Любимое") { set -> set.userLikeIds.contains(user.email) }
        )
    }

    ScreenView(title = user.nickname, showBack = visit) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 1) mains
            if (user.mains != null && user.mains?.isNotEmpty() == true) {
                MainsRecycler(mains = user.mains!!, showMainStat.value != null) { stats ->
                    if (showMainStat.value == stats)
                        showMainStat.value = null
                    else showMainStat.value = stats
                }
            }
            // Tabs
            ITabRow(pagerState, marginTop = 10.dp) {
                tabs.mapIndexed { i, tabItem ->
                    Tab(
                        modifier = Modifier.zIndex(2f),
                        selected = pagerState.currentPage == i,
                        onClick = {
                            scope.launch { pagerState.animateScrollToPage(i)
                                viewModel.filterSets(tabItem.onClick)
                            } },
                        selectedContentColor = PrimaryDark,
                        unselectedContentColor = PrimaryDark
                    ) {
                        Text(
                            tabItem.title,
                            color = if (pagerState.currentPage == i) PrimaryDark
                            else Teal
                        )
                    }
                }
            }
            // 3) recycler
            HorizontalPager(
                pageCount = tabs.size,
                state = pagerState
            ) {
                when (val sets = setsState.value) {
                    emptyList<UserSet>() -> CenteredBox {
                        Text(text = "Список пуст...", color = Teal200)
                    }
                    else -> SetsRecycler(sets, visit, it == 1)
                }
            }
        }
    }
    val isShowMainStat = remember { mutableStateOf(false) }

    if (isShowMainStat.value && showMainStat.value != null) {
        CenteredBox {
            Dialog(onDismissRequest = { isShowMainStat.value = false }) {
                Text(
                    text = buildAnnotatedString {
                        val stat = showMainStat.value!!
                        append("Килы: ")
                        withStyle(SpanStyle(color = Color.White, fontStyle = FontStyle.Italic)) {
                            append("${stat.kills}\n")
                        }
                        append("Процент побед: ")
                        withStyle(SpanStyle(color = Color.White, fontStyle = FontStyle.Italic)) {
                            append("${stat.winrate}\n")
                        }
                        append("Герой воскресил: ")
                        withStyle(SpanStyle(color = Color.White, fontStyle = FontStyle.Italic)) {
                            append("${stat.revives}")
                        }
                    },
                    color = Teal200,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(PrimaryDark, RoundedCornerShape(20.dp))
                        .border(1.dp, Teal200, RoundedCornerShape(20.dp))
                        .padding(20.dp)
                        .clickable { isShowMainStat.value = false }
                )
            }
        }
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.filterSets(tabs[page].onClick)
        }
    }
    DisposableEffect(pagerState) {
        viewModel.filterSets(tabs[0].onClick)
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun MainsRecycler(mains: Map<String, Stats>, isSelected: Boolean, onItemClick: (Stats) -> Unit) {
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        mains.map { main ->
            item {
                MainsItem(
                    Pair(main.key, main.value), isSelected,
                    isStart = (mains.keys.first() == main.key),
                    isEnd = (mains.keys.last() == main.key),
                    onItemClick
                )
            }
        }
    }
}

@Composable
fun SetsRecycler(
    sets: List<UserSet>,
    visit: Boolean = false,
    isShowLiked: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        ScreenWithDelete { delete ->
            LazyColumn (
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Transparent),
            ) {
                items(sets) { set ->
                    SetInProfileCard(set, visit || isShowLiked, disableSettings = visit) { s ->
                        delete.showDelete("сет") {

                        }
                    }
                }
            }
        }
    }
}

data class ProfileTab(
    val title: String,
    val onClick: (UserSet) -> Boolean
)

@Composable
fun MainsItem(
    main: Pair<String, Stats>,
    isSelected: Boolean,
    isStart: Boolean, isEnd: Boolean,
    onItemClick: (Stats) -> Unit
) {
    val padding = if (isStart) PaddingValues(start = 20.dp, end = 5.dp)
    else if (isEnd) PaddingValues(start = 5.dp, end = 20.dp)
    else PaddingValues(horizontal = 5.dp)
    VCenteredBox (modifier = Modifier.padding(padding)) {
        Row(
            modifier = Modifier
                .background(
                    color = if (isSelected) Teal200 else PrimaryDark,
                    shape = RoundedCornerShape(20.dp)
                )
                .border(2.dp, Teal200, RoundedCornerShape(50.dp))
                .clickable { onItemClick.invoke(main.second) }
                .padding(5.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = main.first,
                color = if (isSelected) PrimaryDark else Teal200
            )
        }
    }
}
