@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.mechanics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.data.description.Parser
import com.bulkapedia.compose.elements.Block
import com.bulkapedia.compose.elements.ScreenWithError
import com.bulkapedia.compose.elements.YouTubeScreen
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun MechanicScreen(ctx: ToolbarCtx, id: String, viewModel: MechanicViewModel) {
    // Toolbar
    ctx.observeAsState()
    ctx.setData(title = "Механика", showBackButton = true)
    // Vars
    val parser = Parser()
    val showError = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }
    // UI
    val viewState = viewModel.mechanic.observeAsState()
    ScreenWithError(showError,
        text = errorMessage.value,
        onClose = {  }
    ) {
        when (val state = viewState.value) {
            null -> {
                showError.value = true
                errorMessage.value = "Не удалось загрузить данные"
            }
            else -> {
                YouTubeScreen(state.video) {
                    Block(marginStart = 0.dp, marginEnd = 0.dp, marginTop = 20.dp, marginBottom = 0.dp) {
                        Text(text = "Описание", color = Teal200, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = parser.parseToAnnotated(state.description),
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = 10.dp)
                        )
                    }
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