package com.bulkapedia.compose.screens.heroinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.data.repos.hero_info.HeroInfo
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.util.stringToResource

@Composable
fun HeroesInfoScreen(viewModel: HeroesInfoViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val heroesInfo by viewModel.heroesInfoFlow.collectAsState()
    ScreenView(title = "Выберите героя", showBack = true) {
        LazyVerticalGrid(
            GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .padding(horizontal = 20.dp)
        ) {
            items(heroesInfo, key = { it.id }) { info ->
                HeroInfoCard(info) { id ->
                    navController.navigate("${Destinations.HERO_INFO}/$id")
                }
            }
        }
    }
    // Listener
    DisposableEffect(null) {
        viewModel.listenHeroInfo()
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun HeroInfoCard(
    heroInfo: HeroInfo,
    onClick: (String) -> Unit
) {
    OutlinedCard(
        modifier = Modifier.size(200.dp, 230.dp),
        onClick = { onClick(heroInfo.id) }
    ) {
        Image(
            painter = painterResource(stringToResource(heroInfo.hero)),
            contentDescription = heroInfo.hero,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}
