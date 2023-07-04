package com.bulkapedia.compose.screens.dashboard.screens.heroinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.data.hero_info.HeroInfo
import com.bulkapedia.compose.data.classes.ChangeValues
import com.bulkapedia.compose.data.classes.Value
import com.bulkapedia.compose.elements.OutlinedButton
import com.bulkapedia.compose.elements.ScreenWithChangesDialog
import com.bulkapedia.compose.screens.heroinfo.HeroInfoCard
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun HeroesInfoScreen(viewModel: HeroInfoViewModel = hiltViewModel()) {
    ScreenView(title = "Гайды на персонажей", showBack = true) {

    }
    // ChangeValues
    val showDialog = remember { mutableStateOf(false) }
    val dialogTitle = remember { mutableStateOf("Добавить") }
    val infoText = remember { mutableStateOf("") }
    val fieldLabels = remember { mutableStateOf(listOf<String>()) }
    val values = remember { mutableStateOf(listOf<Value>()) }
    val onSave = remember { mutableStateOf<(List<Value>) -> Unit>({}) }

    val changeValues = ChangeValues(
        show = showDialog,
        title = dialogTitle,
        fieldLabels = fieldLabels,
        values = values,
        infoText = infoText,
        onSave = onSave
    )
    // UI
//    ScreenWithChangesDialog(changeValues) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(fraction = 0.923f)
//                .background(Primary),
//        ) {
//            when (val state = viewState.value!!) {
//                emptyList<HeroInfo>() -> CenteredBox { Text("Список пуст...", color = Teal200) }
//                else -> {
//                    LazyVerticalGrid(
//                        GridCells.Fixed(2),
//                        horizontalArrangement = Arrangement.spacedBy(20.dp),
//                        verticalArrangement = Arrangement.spacedBy(20.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Primary)
//                            .padding(horizontal = 20.dp)
//                    ) {
//                        items(state, key = { it.id }) { info ->
//                            HeroInfoCard(info) {
//                                changeValues.apply {
//                                    title.value = "Изменить"
//                                    fieldLabels.value = listOf(
//                                        "Icon", "Video", "Description"
//                                    )
//                                    values.value = listOf(
//                                        Value.TextValue(mutableStateOf(info.hero)),
//                                        Value.TextValue(mutableStateOf(info.video)),
//                                        Value.TextValue(mutableStateOf(info.description)),
//                                    )
//                                    onSave.value = { newValues ->
//                                        val heroInfo = HeroInfo(
//                                            id = (newValues[0] as Value.TextValue).v.value.split("_")[0],
//                                            hero = (newValues[0] as Value.TextValue).v.value,
//                                            video = (newValues[1] as Value.TextValue).v.value,
//                                            description = (newValues[2] as Value.TextValue).v.value
//                                        )
//                                        viewModel.addHeroInfo(heroInfo)
//                                    }
//                                    show.value = true
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            OutlinedButton(text = "Добавить") {
//                changeValues.apply {
//                    title.value = "Добавить"
//                    fieldLabels.value = listOf(
//                        "Icon", "Video", "Description"
//                    )
//                    values.value = listOf(
//                        Value.TextValue(mutableStateOf("arnie_icon")),
//                        Value.TextValue(mutableStateOf("video_id")),
//                        Value.TextValue(mutableStateOf("[start_time]0:00[end_time] some description")),
//                    )
//                    onSave.value = { newValues ->
//                        val heroInfo = HeroInfo(
//                            id = (newValues[0] as Value.TextValue).v.value.split("_")[0],
//                            hero = (newValues[0] as Value.TextValue).v.value,
//                            video = (newValues[1] as Value.TextValue).v.value,
//                            description = (newValues[2] as Value.TextValue).v.value
//                        )
//                        viewModel.addHeroInfo(heroInfo)
//                    }
//                    show.value = true
//                }
//            }
//        }
//    }
    // Listeners
    DisposableEffect(null) {
        viewModel.listenInfo()
        onDispose { viewModel.removeListener() }
    }
}

