package com.bulkapedia.compose.screens.heroinfo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.data.description.Parser
import com.bulkapedia.compose.elements.Block
import com.bulkapedia.compose.elements.InfoBox
import com.bulkapedia.compose.elements.YouTubeScreen
import com.bulkapedia.compose.screens.Loading
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun HeroInfoScreen(heroId: String, viewModel: HeroInfoViewModel = hiltViewModel()) {

    val parser = Parser()
    val fullScreen = rememberSaveable { mutableStateOf(false) }
    val heroInfoState = viewModel.heroInfoFlow.collectAsState()
    val count by viewModel.heroesInfoCount.collectAsState()

    ScreenView(title = "Гайд", showBack = true) {
        when (val heroInfo = heroInfoState.value) {
            null -> Loading()
            else -> YouTubeScreen(heroInfo.video, fullScreen) {
                /* TODO: Кол-во героев (изменить при добавлении) */
                if (count < 24) {
                    InfoBox(text = "Скоро выйдут гайды и на других персонажей", color = Color.Green, bgColor = PrimaryDark)
                }
                Block(marginStart = 0.dp, marginEnd = 0.dp, marginTop = 20.dp, marginBottom = 0.dp) {
                    Text(text = "Описание", color = Teal200, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = parser.parseToAnnotated(heroInfo.description),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    )
                }
            }
        }
    }
    DisposableEffect(null) {
        viewModel.listenHeroInfo(heroId)
        onDispose { viewModel.removeListener() }
    }
}
