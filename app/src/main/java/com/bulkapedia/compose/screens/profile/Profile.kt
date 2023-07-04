package com.bulkapedia.compose.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
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
import com.bulkapedia.data.mains.Main
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.data.users.User
import com.bulkapedia.compose.elements.mains.MainsRecycler
import com.bulkapedia.compose.elements.sheets.ClosableModalBottomSheet
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
            Profile(args?.getString("email")!!) }, ToSETTINGS,
            ToSIGN_IN, ToDASHBOARD, ToCOMMENTS,
            ToVISIT, ToCREATE_SET, ToDEV_CHAT,
            ToCATEGORY_MANAGE, ToUSERS_SETS, ToMANAGE_HEROES_INFO,
        )
    )
}

@Composable
fun Profile(email: String) { ProfileSheet { it.email == email } }

@Composable
fun VisitProfileScreen(nickname: String) { ProfileSheet(true) { it.nickname == nickname } }

@Composable
fun ProfileSheet(
    visit: Boolean = false,
    viewModel: ProfileViewModel = hiltViewModel(),
    by: (User) -> Boolean
) {
    val userState = viewModel.userFlow.collectAsState()
    val mainStat = remember { mutableStateOf<Main?>(null) }

    ClosableModalBottomSheet(
        sheetContent = { when (val stat = mainStat.value) {
            null -> CenteredBox { Text(text = "") }
            else -> ShowMainSheet(stat)
        } },
        key = mainStat.value,
        onClose = { mainStat.value = null }
    ) {
        when (val user = userState.value) {
            null -> Loading()
            else -> ProfileScreen(user, viewModel, visit, mainStat) {  }
        }
    }
    DisposableEffect(null) {
        viewModel.fetchUser(by)
        onDispose { viewModel.removeListener() }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    user: User,
    viewModel: ProfileViewModel = hiltViewModel(),
    visit: Boolean = false,
    selected: MutableState<Main?>,
    onItemClick: (Main) -> Unit
) {
    val sets = viewModel.userSets
    val mains = viewModel.userMains
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabs = if (visit) {
        listOf(ProfileTab(title = "Cеты") { set -> set.author == user.nickname })
    } else {
        listOf(
            ProfileTab(title = "Ваши сеты") { set -> set.author == user.nickname },
            ProfileTab(title = "Любимое") { set -> set.userLikeIds.contains(user.email) }
        )
    }

    ScreenView(title = user.nickname, showBack = visit) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // 1) mains
            AnimatedVisibility(mains.isNotEmpty()) {
                MainsRecycler(mains, selected, onItemClick)
            }
            // Tabs
            ITabRow(pagerState) {
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
            ) { page ->
                when {
                    sets.isEmpty() -> CenteredBox { Text(text = "Список пуст...", color = Teal200) }
                    else -> SetsRecycler(sets, visit, page == 1, viewModel)
                }
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
    LaunchedEffect(null) { viewModel.fetchMains(user.email) }
}

@Composable
fun SetsRecycler(
    sets: SnapshotStateList<UserSet>,
    visit: Boolean = false,
    isShowLiked: Boolean = true,
    viewModel: ProfileViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        contentAlignment = Alignment.TopCenter
    ) {
        ScreenWithDelete { delete ->
            LazyColumn (
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                items(sets) { set ->
                    SetInProfileCard(set, visit || isShowLiked, disableSettings = visit) { s ->
                        delete.showDelete("сет") { viewModel.deleteSet(s) }
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
fun ShowMainSheet(stats: Main) {
    OutlinedCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),) {
        Text(
            text = buildAnnotatedString {
                append("Килы: ")
                withStyle(SpanStyle(color = Color.White, fontStyle = FontStyle.Italic)) {
                    append("${stats.kills}\n")
                }
                append("Процент побед: ")
                withStyle(SpanStyle(color = Color.White, fontStyle = FontStyle.Italic)) {
                    append("${stats.winrate}%\n")
                }
                append("Герой воскресил: ")
                withStyle(SpanStyle(color = Color.White, fontStyle = FontStyle.Italic)) {
                    append("${stats.revives}")
                }
            },
            color = Teal200,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}

@Composable
fun MainsItem(
    main: Pair<String, Main>,
    isSelected: Boolean,
    isStart: Boolean, isEnd: Boolean,
    onItemClick: (Main) -> Unit
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
