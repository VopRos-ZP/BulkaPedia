@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.maps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import com.bulkapedia.compose.data.*
import com.bulkapedia.compose.elements.Tags
import com.bulkapedia.compose.elements.mapsTags
import com.bulkapedia.compose.models.TagViewModel
import com.bulkapedia.compose.models.TagViewState
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.navigation.navigate
import com.bulkapedia.compose.screens.dashboard.DashboardScreen
import com.bulkapedia.compose.screens.devchat.DevChat
import com.bulkapedia.compose.screens.settings.SettingsScreen
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.stringToResource
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.util.*

@Composable
fun Maps(ctx: ToolbarCtx) {
    // init toolbar
    ctx.observeAsState()
    ctx.setData("Выберите карту", showBackButton = true)
    // body
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.923f)
            .background(Primary)
    ) {
        val tagViewModel = TagViewModel()
        val tagViewState = tagViewModel.viewState.observeAsState()
        Tags(mapsTags(), tagViewModel)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(Color.Transparent, RoundedCornerShape(25.dp))
                .padding(horizontal = 40.dp)
        ) {
            val filteredList = filterList(HardCode.Map.values, tagViewState.value!!)
            items(filteredList) { item ->
                HCenteredBox {
                    MapCard(item, ctx.navController,
                        isLast = filteredList.last() == item
                    )
                }
            }
        }
    }
}

@Composable
fun SelectedMap(ctx: ToolbarCtx, viewModel: MapViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value!!) {
        MapViewState.StateLoading -> Loading()
        MapViewState.StateNoItem -> Loading()
        is MapViewState.StateData -> ShowMap(map = state.map, ctx)
    }
    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainEvent(MapsEvent.EnterScreen)
    })
}

@Composable
private fun ShowMap(map: com.bulkapedia.compose.data.Map<Any?, Any?>, ctx: ToolbarCtx) {
    // init toolbar
    ctx.observeAsState()
    ctx.setData(title = CTX.getString(stringToResource(map.name)), showBackButton = true)
    // map view
    val mapIconState = remember { mutableStateOf(map.image) }
    val toggleTextState = remember { mutableStateOf("Показать точки появления") }
    Column (
        modifier = Modifier.background(Primary)
    ) {
        Card(
            backgroundColor = PrimaryDark,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, Teal200),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 400.dp)
                .padding(20.dp)
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = stringToResource(mapIconState.value)),
                    contentDescription = mapIconState.value,
                    modifier = Modifier.size(maxWidth)
                        .padding(20.dp)
                )
            }
        }
        Card(
            backgroundColor = PrimaryDark,
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(2.dp, Teal200),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            HCenteredBox (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = toggleTextState.value,
                    color = Teal200,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        mapIconState.value = if (mapIconState.value == map.image) map.imageSpawns
                        else map.image
                        toggleTextState.value = if (toggleTextState.value == "Показать точки появления") "Скрыть точки появления"
                        else "Показать точки появления"
                    }
                )
            }
        }
    }
}

@Composable
private fun Loading() {
    val mapSpawnsState = remember { mutableStateOf(false) }
    val toggleTextState = remember { mutableStateOf("Показать точки появления") }
    Column (
        modifier = Modifier
            .background(Primary, RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .padding(20.dp)
    ) {
        HCenteredBox {
            CircularProgressIndicator(color = Color.White)
        }
        HCenteredBox {
            IconToggleButton(checked = mapSpawnsState.value, onCheckedChange = {
                toggleTextState.value = if (toggleTextState.value == "Показать точки появления") "Скрыть точки появления"
                else "Показать точки появления"
            }) {
                HCenteredBox {
                    Text(
                        text = toggleTextState.value,
                        color = Teal200,
                        fontSize = 18.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))
    }
}

@Composable
fun MapCard(map: String, nc: NavController, isLast: Boolean = false) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (isLast) 0.dp else 20.dp),
        shape = RoundedCornerShape(25.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(2.dp, Teal200)
    ) {
        Column (
            modifier = Modifier.padding(bottom = 20.dp, start = 20.dp, end = 20.dp, top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = stringToResource(map.plus("_txt"))),
                textAlign = TextAlign.Center,
                color = Teal200,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Image(
                painter = painterResource(id = stringToResource(map)),
                contentDescription = map,
                modifier = Modifier.clickable {
                    nc.navigate("${Destinations.MAPS}/$map",
                        bundleOf("mapImage" to map))  { launchSingleTop = true }
                }
            )
        }
    }
}

private fun filterList(list: List<String>, tagViewState: TagViewState): List<String> {
    return when (tagViewState.selected) {
        true -> when (val sortType = tagViewState.sortType) {
            is SortType.ByMap -> list.filter { HardCode.Map.getType(it) == sortType.type.str() }
            else -> list
        }
        else -> list
    }
}
