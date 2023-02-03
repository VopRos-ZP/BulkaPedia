@file:Suppress("FunctionName")

package com.bulkapedia.compose.screens.hero

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.heroes.HeroEvent
import com.bulkapedia.compose.screens.heroes.HeroViewModel
import com.bulkapedia.compose.screens.heroes.HeroViewState
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.data.heroes.Hero
import com.bulkapedia.compose.util.stringToResource
import com.bulkapedia.R
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.elements.ConfirmDialog
import com.bulkapedia.compose.elements.ITabRow
import com.bulkapedia.compose.elements.ScreenWithDelete
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.CustomIndicator
import com.bulkapedia.compose.screens.sets.HeroTabEvent
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.compose.screens.sets.SetTabViewModel
import com.bulkapedia.compose.screens.sets.SetTabViewState
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.DataStore
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun HeroSets(ctx: ToolbarCtx, viewModel: HeroViewModel) {
    val viewState = viewModel.heroLiveData.observeAsState()
    when (val state = viewState.value!!) {
        HeroViewState.StateLoading -> HeroSetLoading()
        is HeroViewState.StateData -> HeroSetData(state.hero, ctx, hiltViewModel())
        else -> {}
    }
    LaunchedEffect(viewState) {
        viewModel.obtainEvent(HeroEvent.EnterScreen)
    }
}

@Composable
private fun HeroSetData(
    hero: Hero,
    ctx: ToolbarCtx,
    viewModel: SetTabViewModel
) {
    ctx.observeAsState()
    ctx.setData(hero.name, true)
    // tab sets view
    val viewState = viewModel.liveData.observeAsState()
    val setsState = remember { mutableStateListOf<UserSet>() }
    // UI
    when (val state = viewState.value!!) {
        SetTabViewState.Loading -> HeroSetLoading()
        is SetTabViewState.Error -> {}
        is SetTabViewState.Enter -> HeroContent(ctx, hero, state.state)
    }
    DisposableEffect(null) {
        viewModel.obtainEvent(HeroTabEvent.LoadingData(hero.heroId, setsState))
        onDispose { viewModel.removeListener() }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeroContent(ctx: ToolbarCtx, hero: Hero, state: SnapshotStateList<UserSet>) {
    val tabs = listOf(SetTabItem.Number1, SetTabItem.Number2, SetTabItem.Number3)
    val store = DataStore(LocalContext.current)
    val pagerState = rememberPagerState()
    val nickname by store.getNickname.collectAsState(initial = "")
    val sign by store.getSign.collectAsState(initial = false)
    val scope = rememberCoroutineScope()
    val currentSet = remember { mutableStateOf(state[0]) }
    val showConfirmDialog = remember { mutableStateOf(false) }
    val setConfirmDialog = remember { mutableStateOf<UserSet?>(null) }
    // UI
    LaunchedEffect(state) {
        currentSet.value = state[pagerState.currentPage]
    }
    ScreenWithDelete(
        whatDelete = "Сет",
        show = showConfirmDialog,
        whenShow = { setConfirmDialog.value != null },
        onDelete = { Database().removeSet(setConfirmDialog.value!!) }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
        ) {
            // hero icon and difficult
            TopHeroCard(icon = hero.icon) {
                Text(
                    text = stringResource(R.string.difficult),
                    color = Teal200,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                HeroDifficult(difficult = hero.difficult)
            }
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary)
            ) {
                item {
                    ITabRow(pagerState) {
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
                                    tabItem.title,
                                    color = if (pagerState.currentPage == i) PrimaryDark
                                    else Teal
                                )
                            }
                        }
                    }
                }
                item {
                    CenteredBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent, RoundedCornerShape(20.dp))
                    ) {
                        HorizontalPager(
                            count = state.size,
                            state = pagerState
                        ) {
                            SetTabCard(currentSet.value, currentSet.value.from != nickname) { s ->
                                setConfirmDialog.value = s
                                showConfirmDialog.value = true
                            }
                        }
                    }
                }
                // add set button
                item {
                    HCenteredBox(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    ) {
                        AddSetButton(ctx, hero.heroId, sign ?: false, nickname ?: "")
                    }
                }
                // counterpicks
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(start = 5.dp, top = 20.dp, end = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HCenteredBox {
                            Text(
                                text = stringResource(id = R.string.counterpick),
                                color = Teal200,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 15.dp)
                            )
                        }
                        Counterpicks(icons = hero.counterpicks)
                    }
                }
                item {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp))
                }
            }
        }
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            currentSet.value = state[page]
        }
    }
}

@Composable
private fun HeroSetLoading() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        CenteredBox { CircularProgressIndicator(color = Teal200) }
    }
}

@Composable
fun TopHeroCard(icon: String, trailingContent: @Composable ColumnScope.() -> Unit) {
    HCenteredBox (
        modifier = Modifier
            .fillMaxWidth()
            .height((40 + 180).dp)
            .padding(20.dp)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .border(2.dp, Teal200, RoundedCornerShape(20.dp))
    ) {
        Row {
            Image(
                painter = painterResource(id = stringToResource(icon)),
                contentDescription = icon,
                modifier = Modifier
                    .size(width = 150.dp, height = 180.dp)
                    .padding(20.dp)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = trailingContent
            )
        }
    }
}

@Composable
fun HeroDifficult(difficult: String) {
    val colors = getDifficultImages(difficult)
    Row (
        modifier = Modifier.background(Color.Transparent)
    ) {
        colors.map {
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(width = 50.dp, height = 15.dp)
                    .background(it, RectangleShape)
                    .border(2.dp, Teal200, RectangleShape)
            )
        }
    }
}

private fun getDifficultImages(difficult: String): List<Color> {
    return when (difficult) {
        "easy" -> listOf(Color.Yellow, Color.Transparent, Color.Transparent)
        "normal" -> listOf(Color.Yellow, Color.Yellow, Color.Transparent)
        "hard" -> listOf(Color.Yellow, Color.Yellow, Color.Yellow)
        else -> emptyList()
    }
}

@Composable
fun AddSetButton(ctx: ToolbarCtx, hero: String, sign: Boolean, nickname: String) {
    Card(
        elevation = 10.dp,
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(50.dp)
    ) {
        Button(
            onClick = {
                if (sign && nickname.isNotEmpty()) {
                    ctx.navController.navigate("${Destinations.CREATE_SET}/$hero/${nickname}")
                } else {
                    /* Сообщение о том, что надо войти в аккаунт */
                }
                      },
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryDark),
            shape = RoundedCornerShape(50.dp),
            border = BorderStroke(2.dp, Teal200)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "add_icon",
                tint = Teal200,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = "Добавить сет",
                color = Teal200
            )
        }
    }
}

@Composable
fun Counterpicks(icons: List<String>) {
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        items(icons) {
            Image(
                painter = painterResource(id = stringToResource(it)),
                contentDescription = it,
                modifier = Modifier.size(width = 150.dp, height = 180.dp)
            )
        }
    }
}