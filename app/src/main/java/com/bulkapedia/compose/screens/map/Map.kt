package com.bulkapedia.compose.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.screens.Loading
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

private const val showSpawnsText = "Показать точки появления"
private const val hideSpawnsText = "Скрыть точки появления"

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SelectedMapScreen(mapImage: String, viewModel: MapViewModel = hiltViewModel()) {
    val mapState by viewModel.mapFlow.collectAsState()
    ScreenView(title = mapState?.name ?: "Загрузка...", showBack = true) {
        when (val map = mapState) {
            null -> Loading()
            else -> {
                val mapIconState = remember { mutableStateOf(map.image) }
                val toggleTextState = remember { mutableStateOf(showSpawnsText) }
                Column (
                    modifier = Modifier.background(Primary)
                ) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 400.dp)
                            .padding(20.dp)
                    ) {
                        GlideImage(
                            model = mapIconState.value,
                            contentDescription = mapIconState.value,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        HCenteredBox(
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
                                    toggleTextState.value = if (toggleTextState.value == showSpawnsText) hideSpawnsText
                                    else showSpawnsText
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    DisposableEffect(null) {
        viewModel.fetchMap(mapImage)
        onDispose { viewModel.dispose() }
    }
}