@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.heroinfo

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.data.description.Parser
import com.bulkapedia.compose.elements.Block
import com.bulkapedia.compose.elements.InfoBox
import com.bulkapedia.compose.elements.YouTubeScreen
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun HeroInfoScreen(ctx: ToolbarCtx, heroId: String, viewModel: HeroInfoViewModel) {
    // Toolbar
    ctx.observeAsState()
    ctx.setData(title = "Гайд", showBackButton = true)
    // State
    val parser = Parser()
    val viewState = viewModel.heroData.observeAsState()
    // UI
    val pair = viewState.value!!
    when (pair.first) {
        null -> CenteredBox { CircularProgressIndicator(color = Teal200) }
        else -> {
            YouTubeScreen(pair.first!!.video) {
                /* TODO: Кол-во героев (изменить при добавлении) */
                if (pair.second < 23) {
                    InfoBox(text = "Скоро выйдут гайды и на других персонажей", color = Color.Green, bgColor = PrimaryDark)
                }
                Block(marginStart = 0.dp, marginEnd = 0.dp, marginTop = 20.dp, marginBottom = 0.dp) {
                    Text(text = "Описание", color = Teal200, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = parser.parseToAnnotated(pair.first!!.description),
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 10.dp)
                    )
                }
            }
        }
    }
    // Listener
    DisposableEffect(null) {
        viewModel.listenHeroInfo(heroId)
        onDispose { viewModel.removeListener() }
    }
}
