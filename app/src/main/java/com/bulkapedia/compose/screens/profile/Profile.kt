@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.bulkapedia.compose.data.NavigationScreen
import com.bulkapedia.compose.data.*
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.screens.profile.visit.VisitProfileViewModel
import com.bulkapedia.compose.screens.profile.visit.VisitProfileViewState
import com.bulkapedia.compose.screens.sets.SetInProfileCard
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.labels.Stats
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.data.User
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun ProfileNav(toolbarCtx: ToolbarCtx, startDestination: String, dEmail: String) {
    Navigation(startDestination, toolbarCtx = toolbarCtx,
        screens = listOf(
            NavigationScreen(startDestination,
                listOf(navArg("email", NavType.StringType, dEmail))) { ctx, args ->
                val email = args?.getString("email") ?: ""
                val viewModel = hiltViewModel<ProfileViewModel>()
                Profile(ctx, email, viewModel)
            }, ToSETTINGS,
            ToSIGN_IN, ToDASHBOARD, ToCOMMENTS,
            ToVISIT, ToCREATE_SET, ToDEV_CHAT,
            ToCATEGORY_MANAGE, ToUSERS_SETS, ToMANAGE_HEROES_INFO,
        )
    )
}

@Composable
fun Profile(ctx: ToolbarCtx, email: String, viewModel: ProfileViewModel) {
    val viewState = viewModel.liveData.observeAsState()
    ScreenWithError { action ->
        when (val state = viewState.value!!) {
            is ProfileViewState.EnterScreen -> ProfileScreen(
                ctx, user = state.user,
                viewModel = hiltViewModel(),
                visit = false
            )
            is ProfileViewState.ErrorScreen -> {
                action.showError(state.message) {
                    viewModel.obtainEvent(ProfileEvent.Loading(email))
                }
            }
            else -> LoadingProfile()
        }
    }
    DisposableEffect(ctx.navController.currentDestination) {
        viewModel.obtainEvent(ProfileEvent.Loading(email))
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun VisitProfileScreen(ctx: ToolbarCtx, nickname: String, viewModel: VisitProfileViewModel) {
    val viewState = viewModel.liveData.observeAsState()
    ScreenWithError { action ->
        when (val state = viewState.value!!) {
            is VisitProfileViewState.Enter -> ProfileScreen(
                ctx, state.user, hiltViewModel(), true
            )
            is VisitProfileViewState.Error -> action.showError(state.message) {
                viewModel.fetchUser(nickname)
            }
            else -> LoadingProfile()
        }
    }
    LaunchedEffect(ctx.navController.currentDestination) {
        viewModel.fetchUser(nickname)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(
    ctx: ToolbarCtx,
    user: User,
    viewModel: SetsProfileViewModel,
    visit: Boolean = false
) {
    // Init toolbar
    ctx.observeAsState()
    ctx.setData(title = user.nickname, showBackButton = visit)
    // variables
    val viewState = viewModel.liveData.observeAsState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val startOnClick: (UserSet) -> Boolean = { it.from == user.nickname }
    val setsState = remember { mutableStateListOf<UserSet>() }
    val isShowLiked = remember { mutableStateOf(false) }
    val isShowMainStat = remember { mutableStateOf(false) }
    val showMainStat = remember { mutableStateOf<Stats?>(null) }
    val tabs = if (visit) {
        listOf(
            ProfileTab(title = "Сеты") {
                viewModel.obtainEvent(SetsProfileEvent.OnProfileTabClick(startOnClick, setsState))
            }
        )
    } else {
        listOf(
            ProfileTab(title = "Ваши сеты") {
                viewModel.obtainEvent(SetsProfileEvent.OnProfileTabClick(startOnClick, setsState))
            },
            ProfileTab(title = "Любимое") {
                viewModel.obtainEvent(SetsProfileEvent.OnProfileTabClick(
                    { it.userLikeIds.contains(user.email) },
                    setsState
                ))
            }
        )
    }
    // UI
    Column(modifier = Modifier.fillMaxSize()) {
        // 1) mains
        if (user.mains != null && user.mains?.isNotEmpty() == true) {
            MainsRecycler(mains = user.mains!!, isShowMainStat.value) { stats ->
                showMainStat.value = stats
                isShowMainStat.value = !isShowMainStat.value
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
                            tabItem.onClick.invoke()
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
            count = tabs.size,
            state = pagerState
        ) { SetsRecycler(viewState, visit, isShowLiked.value) }
    }
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
            tabs[page].onClick.invoke()
            isShowLiked.value = page == 1
        }
    }
    DisposableEffect(pagerState) {
        viewModel.obtainEvent(SetsProfileEvent.OnProfileTabClick(startOnClick, setsState))
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun LoadingProfile() {
    CenteredBox (modifier = Modifier.fillMaxSize().background(Primary)) {
        CircularProgressIndicator(color = Teal200)
    }
}

@Composable
fun MainsRecycler(mains: Map<String, Stats>, isSelected: Boolean, onItemClick: (Stats) -> Unit) {
    LazyRow (
        modifier = Modifier.fillMaxWidth()
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
    viewState: State<SetsProfileViewState?>,
    visit: Boolean = false,
    isShowLiked: Boolean = true
) {
    val confirmDialog = remember { mutableStateOf(false) }
    val confirmSet = remember { mutableStateOf<UserSet?>(null) }
    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        ScreenWithDelete(
            whatDelete = "сет",
            show = confirmDialog,
            whenShow = { confirmSet.value != null },
            onDelete = { Database().removeSet(confirmSet.value!!) }
        ) {
            ScreenWithError { action ->
                when (val state = viewState.value!!) {
                    is SetsProfileViewState.EnterScreen -> {
                        LazyColumn (
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(Color.Transparent),
                        ) {
                            val sets = state.sets
                            if (sets.isNotEmpty()) {
                                sets.map { uSet ->
                                    item {
                                        SetInProfileCard(uSet, visit || isShowLiked, disableSettings = visit) { s ->
                                            confirmSet.value = s
                                            confirmDialog.value = true
                                        }
                                    }
                                }
                            } else {
                                item { CenteredBox {
                                    Text(
                                        text = "Список пуст...",
                                        color = Teal200,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 20.dp)
                                    )
                                } }
                            }
                        }
                    }
                    is SetsProfileViewState.ErrorScreen -> action.showError(state.message)
                    else -> CenteredBox (
                        modifier = Modifier.fillMaxSize()
                            .background(Color.Transparent)
                    ) { CircularProgressIndicator(color = Teal200) }
                }
            }
        }
    }
}

data class ProfileTab(
    val title: String,
    val onClick: () -> Unit
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
