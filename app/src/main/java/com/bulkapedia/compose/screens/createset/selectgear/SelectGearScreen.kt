package com.bulkapedia.compose.screens.createset.selectgear

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.screens.sets.GearImage
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.data.gears.Effect
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.gears.Ranks
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.stringToResource
import com.bulkapedia.data.gears.Gear.Companion.emptyIcons
import com.bulkapedia.data.gears.Gear.Companion.getRankEffect
import kotlinx.coroutines.launch

@Composable
fun SelectGearScreen(
    showSelectGears: MutableState<Boolean>,
    cellState: MutableState<GearCell>,
    hero: Hero,
    gearsState: MutableState<Map<GearCell, Gear>>,
    viewModel: SelectGearViewModel
) {
    val gears = viewModel.gears
    SelectGearFragment(gears, showSelectGears, cellState.value, gearsState)
    DisposableEffect(cellState) {
        viewModel.fetchGears(cellState.value, hero)
        onDispose { viewModel.dispose() }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectGearFragment(
    gears: SnapshotStateList<Gear>,
    showSelectGears: MutableState<Boolean>,
    cell: GearCell,
    gearsState: MutableState<Map<GearCell, Gear>>
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    val sheetGear = remember { mutableStateOf<Gear?>(null) }

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
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .border(2.dp, Teal200, RoundedCornerShape(20.dp)),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(gears) { gear ->
                SelectGearItem(gear) {
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
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .padding(start = 15.dp)
            .clickable(onClick)
    ) {
        GearImage(gear.icon) {}
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryDark, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val list = gear.getRankEffect().toList()
        items(list) {
            SelectRankGearItem(it.first, it.second, state) { newEff ->
                val newGear = gear.copy(effects = newEff)
                onClick(newGear)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectRankGearItem(
    rank: Ranks, effs: List<Effect>,
    state: BottomSheetState,
    onClick: (List<Effect>) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val color = getColorByRank(rank)
    OutlinedButton(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, color),
        onClick = {
            scope.launch { state.collapse() }
            onClick.invoke(effs)
        },
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
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
