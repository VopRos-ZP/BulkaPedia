package com.bulkapedia.compose.screens.hero

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.stringToResource
import com.bulkapedia.R
import com.bulkapedia.compose.elements.ITabRow
import com.bulkapedia.compose.elements.ScreenWithDelete
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.screens.Loading
import com.bulkapedia.compose.screens.titled.ScreenView
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroSets(heroId: String, viewModel: HeroViewModel = hiltViewModel()) {
    val heroState = viewModel.heroFlow.collectAsState()
    val sets = viewModel.setsFlow
    val navController = LocalNavController.current
    when (val hero = heroState.value) {
        null -> Loading()
        else -> {
            val tabs = listOf(SetTabItem.Number1, SetTabItem.Number2, SetTabItem.Number3)
            val store = DataStore(LocalContext.current)
            val pagerState = rememberPagerState()
            val nickname by store.getNickname.collectAsState(initial = "")
            val sign by store.getSign.collectAsState(initial = false)
            val scope = rememberCoroutineScope()
            TextSnackbar { snackbar ->
                ScreenView(title = hero.name, showBack = true) {
                    ScreenWithDelete { delete ->
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
                                                onClick = { scope.launch { pagerState.animateScrollToPage(i) } },
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
                                            .background(
                                                Color.Transparent,
                                                RoundedCornerShape(20.dp)
                                            )
                                    ) {
                                        if (sets.isEmpty()) {
                                            CenteredBox { Text("Сетов нет", color = Teal200) }
                                        } else {
                                            HorizontalPager(
                                                state = pagerState,
                                                pageCount = sets.size
                                            ) { page ->
                                                val set = sets[page]
                                                SetTabCard(set, set.from != nickname) { s ->
                                                    delete.showDelete("сет") {
                                                        viewModel.deleteUserSet(s)
                                                    }
                                                }
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
                                        AddSetButton {
                                            if (sign && nickname != "") {
                                                navController.navigate("${Destinations.CREATE_SET}/${hero.id}/${nickname}")
                                            } else {
                                                scope.launch {
                                                    snackbar.showSnackbar("Чтобы создать сет необходимо войти в аккаунт!")
                                                }
                                            }
                                        }
                                    }
                                }
                                // counterpick
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
                            }
                        }
                    }
                }
            }
        }
    }
    DisposableEffect(null) {
        viewModel.fetchHero(heroId)
        onDispose { viewModel.dispose() }
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
fun AddSetButton(onClick: () -> Unit) {
    Card(
        elevation = 10.dp,
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(50.dp)
    ) {
        Button(
            onClick = onClick,
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