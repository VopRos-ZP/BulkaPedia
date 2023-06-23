package com.bulkapedia.compose.screens.mechanics.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.data.description.Parser
import com.bulkapedia.compose.elements.Block
import com.bulkapedia.compose.elements.YouTubeScreen
import com.bulkapedia.compose.screens.Loading
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun MechanicScreen(id: String, viewModel: MechanicViewModel = hiltViewModel()) {
    val parser = Parser()

    val fullScreen = remember { mutableStateOf(false) }
    val mechanicState = viewModel.mechanicFlow.collectAsState()

    ScreenView(title = "Механика", showBack = true) {
        when (val mechanic = mechanicState.value) {
            null -> Loading()
            else -> YouTubeScreen(mechanic.video, fullScreen) {
                Block(marginStart = 0.dp, marginEnd = 0.dp, marginTop = 20.dp, marginBottom = 0.dp) {
                    Text(text = "Описание", color = Teal200, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = parser.parseToAnnotated(mechanic.description),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    )
                }
            }
        }
    }
    // Listener
    DisposableEffect(null) {
        viewModel.listenMechanic(id)
        onDispose { viewModel.removeListener() }
    }
}