@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.dashboard.screens.heroinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.data.category.HeroInfo
import com.bulkapedia.compose.data.classes.ChangeValues
import com.bulkapedia.compose.elements.OutlinedButton
import com.bulkapedia.compose.elements.ScreenWithChangesDialog
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.heroinfo.HeroInfoCard
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun HeroesInfoScreen(ctx: ToolbarCtx, viewModel: HeroInfoViewModel) {
    ctx.observeAsState()
    ctx.setData(title = "Гайды на персонажей", showBackButton = true)
    val viewState = viewModel.liveData.observeAsState()
    // ChangeValues
    val showDialog = remember { mutableStateOf(false) }
    val dialogTitle = remember { mutableStateOf("Добавить") }
    val infoText = remember { mutableStateOf("") }
    val fieldLabels = remember { mutableStateOf(listOf<String>()) }
    val values = remember { mutableStateOf(listOf(mutableStateOf(""))) }
    val onSave = remember { mutableStateOf<(List<String>) -> Unit>({}) }

    val changeValues = ChangeValues(
        show = showDialog,
        title = dialogTitle,
        fieldLabels = fieldLabels,
        values = values,
        infoText = infoText,
        onSave = onSave
    )
    // UI
    ScreenWithChangesDialog(changeValues) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Primary),
        ) {
            when (val state = viewState.value!!) {
                emptyList<HeroInfo>() -> CenteredBox { Text("Список пуст...", color = Teal200) }
                else -> {
                    LazyVerticalGrid(
                        GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.fillMaxSize()
                            .background(Primary)
                            .padding(horizontal = 20.dp)
                    ) {
                        items(state, key = { it.id }) { info ->
                            HeroInfoCard(info) {
                                changeValues.apply {
                                    title.value = "Изменить"
                                    fieldLabels.value = listOf(
                                        "Icon", "Video", "Description"
                                    )
                                    values.value = listOf(
                                        mutableStateOf(info.hero),
                                        mutableStateOf(info.video),
                                        mutableStateOf(info.description),
                                    )
                                    onSave.value = { newValues ->
                                        val heroInfo = HeroInfo(
                                            id = newValues[0].split("_")[0],
                                            hero = newValues[0],
                                            video = newValues[1],
                                            description = newValues[2]
                                        )
                                        viewModel.addHeroInfo(heroInfo)
                                    }
                                    show.value = true
                                }
                            }
                        }
                    }
                }
            }
            OutlinedButton(text = "Добавить") {
                changeValues.apply {
                    title.value = "Добавить"
                    fieldLabels.value = listOf(
                        "Icon", "Video", "Description"
                    )
                    values.value = listOf(
                        mutableStateOf("arnie_icon"),
                        mutableStateOf("video_id"),
                        mutableStateOf("[start_time]0:00[end_time] some description"),
                    )
                    onSave.value = { newValues ->
                        val heroInfo = HeroInfo(
                            id = newValues[0].split("_")[0],
                            hero = newValues[0],
                            video = newValues[1],
                            description = newValues[2]
                        )
                        viewModel.addHeroInfo(heroInfo)
                    }
                    show.value = true
                }
            }
        }
    }
    // Listeners
    DisposableEffect(null) {
        viewModel.listenInfo()
        onDispose { viewModel.removeListener() }
    }
}

