@file:Suppress("FunctionName")

package com.bulkapedia.compose.screens.createset.selectgear

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.createset.emptyIcons
import com.bulkapedia.compose.screens.sets.GearImage
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Purple500
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.gears.Effect
import com.bulkapedia.compose.data.gears.Gear
import com.bulkapedia.compose.data.gears.getRankEffect
import com.bulkapedia.compose.data.heroes.Hero
import com.bulkapedia.compose.data.labels.Ranks
import com.bulkapedia.compose.data.sets.GearCell
import com.bulkapedia.compose.elements.ScreenWithError
import com.bulkapedia.compose.util.stringToResource
import kotlinx.coroutines.launch

@Composable
fun SelectGearScreen(
    ctx: ToolbarCtx,
    showSelectGears: MutableState<Boolean>,
    cellState: MutableState<GearCell>,
    hero: Hero,
    gearsState: MutableState<Map<GearCell, Gear>>,
    viewModel: SelectGearViewModel
) {
    // UI
    val viewState = viewModel.liveData.observeAsState()
    ScreenWithError { action ->
        when (val state = viewState.value!!) {
            SelectGearViewState.Loading -> {}
            is SelectGearViewState.Enter -> SelectGearFragment(
                ctx, state.gears,
                showSelectGears,
                cellState.value, gearsState,
            )
            is SelectGearViewState.Error -> action.showError(state.message) {
                viewModel.obtainEvent(SelectGearEvent.LoadingData(cellState.value, hero))
            }
        }
    }
    LaunchedEffect(key1 = cellState) {
        viewModel.obtainEvent(SelectGearEvent.LoadingData(cellState.value, hero))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectGearFragment(
    ctx: ToolbarCtx,
    gears: List<Gear>,
    showSelectGears: MutableState<Boolean>,
    cell: GearCell,
    gearsState: MutableState<Map<GearCell, Gear>>
) {
    ctx.observeAsState()
    ctx.setData("Выберите снаряжение", false)
    // Vars
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    val sheetGear = remember { mutableStateOf<Gear?>(null) }
    // UI
    BottomSheetScaffold(sheetContent = {
        if (sheetGear.value != null) {
            if (emptyIcons.contains(sheetGear.value!!.icon)) {
                LaunchedEffect(null) {
                    scaffoldState.bottomSheetState.collapse()
                }
                val newMap = gearsState.value.toMutableMap()
                newMap[cell] = sheetGear.value!!
                gearsState.value = newMap
                showSelectGears.value = false
            } else {
                SelectRankGear(sheetGear.value!!, scaffoldState.bottomSheetState) { gear ->
                    val newMap = gearsState.value.toMutableMap()
                    newMap[cell] = gear
                    gearsState.value = newMap
                    showSelectGears.value = false
                }
            }
        } else {
            Text("", color = Color.Transparent)
        }
    },
        sheetBackgroundColor = Primary,
        backgroundColor = Primary,
        sheetPeekHeight = 0.dp,
        sheetGesturesEnabled = false,
        scaffoldState = scaffoldState
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 0.923f)
                .padding(20.dp)
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .padding(10.dp)
        ) {
            items(gears) { gear ->
                SelectGearItem(gear, isLast = gears.last() == gear) {
                    scope.launch {
                        sheetGear.value = gear
                        if (scaffoldState.bottomSheetState.isExpanded) scaffoldState.bottomSheetState.collapse()
                        else scaffoldState.bottomSheetState.expand()
                    }
                }
            }
        }
    }
}

@Composable
fun SelectGearItem(
    gear: Gear,
    isLast: Boolean,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = if (isLast) 0.dp else 10.dp)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .padding(start = 15.dp)
            .clickable(onClick)
    ) {
        GearImage(gear.icon, onClick = onClick)
        Box(
            modifier = Modifier.fillMaxWidth()
                .height((75 + 5).dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier,
                text = gear.effects.toStr(LocalContext.current),
                color = Teal,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectRankGear(
    gear: Gear,
    state: BottomSheetState,
    onClick: (Gear) -> Unit
) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth()
            .background(PrimaryDark, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        val list = gear.getRankEffect().toList()
        itemsIndexed(list) { i, it ->
            SelectRankGearItem(it.first, it.second, isLast = list.lastIndex == i, state) { newEff ->
                gear.effects = newEff
                onClick.invoke(gear)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectRankGearItem(
    rank: Ranks, effs: List<Effect>,
    isLast: Boolean,
    state: BottomSheetState,
    onClick: (List<Effect>) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val color = getColorByRank(rank)
    val padding = PaddingValues(
        start = 20.dp, end = 20.dp,
        top = 5.dp, bottom = if (isLast) 5.dp else 0.dp
    )
    OutlinedButton(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, color),
        onClick = {
            scope.launch { state.collapse() }
            onClick.invoke(effs)
        },
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth()
            .padding(padding)
    ) {
        Text(
            text = effs.joinToString("\n") { it.toString(context) },
            color = color,
            textAlign = TextAlign.Center
        )
    }
}

private fun getColorByRank(rank: Ranks): Color {
    return when (rank) {
        Ranks.COMMON -> Color.Gray
        Ranks.RARE -> Color.Green
        Ranks.EPIC -> Color(8, 159, 252)
        Ranks.LEGENDARY -> Color.Yellow
        Ranks.MYTHIC -> Color(252, 126, 8)
        Ranks.SUPREME -> Color.Red
        Ranks.ULTIMATE -> Color.Magenta
        Ranks.CELESTIAL -> Color(2, 59, 184)
        Ranks.STELLAR -> Purple500
    }
}

fun List<Effect>.toStr(ctx: Context): String {
    val buffer = StringBuilder()
    forEach { e ->
        val num = e.number
        if (num > 0) buffer.append("+")
        if (num != 0) buffer.append(num)
        if (e.percent) buffer.append("%")
        buffer.append(" ${ctx.getString(stringToResource(e.description))}")
        if (last() != e) buffer.append("\n")
    }
    return buffer.toString()
}

fun Effect.toString(ctx: Context): String {
    val buffer = StringBuilder()
    val num = number
    if (num > 0) buffer.append("+")
    if (num != 0) buffer.append(num)
    if (percent) buffer.append("%")
    buffer.append(" ${ctx.getString(stringToResource(description))}")
    return buffer.toString()
}
