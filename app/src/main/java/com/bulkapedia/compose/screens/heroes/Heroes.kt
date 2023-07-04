package com.bulkapedia.compose.screens.heroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.elements.heroesTags
import com.bulkapedia.compose.elements.OutlinedButton
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.navigation.ToCATEGORY_MANAGE
import com.bulkapedia.compose.navigation.ToCOMMENTS
import com.bulkapedia.compose.navigation.ToCREATE_SET
import com.bulkapedia.compose.navigation.ToDASHBOARD
import com.bulkapedia.compose.navigation.ToDEV_CHAT
import com.bulkapedia.compose.navigation.ToHEROES
import com.bulkapedia.compose.navigation.ToMANAGE_HEROES_INFO
import com.bulkapedia.compose.navigation.ToSET
import com.bulkapedia.compose.navigation.ToSETTINGS
import com.bulkapedia.compose.navigation.ToTOP
import com.bulkapedia.compose.navigation.ToUSERS_SETS
import com.bulkapedia.compose.navigation.ToVISIT
import com.bulkapedia.compose.screens.card_recycler.TagsWithRecycler
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.util.stringToResource

@Composable
fun HeroesNavList() {
    Navigation(
        startDestination = Destinations.HEROES,
        screens = listOf(
            ToHEROES, ToDASHBOARD, ToDEV_CHAT, ToCREATE_SET,
            ToSETTINGS, ToSET, ToTOP, ToCOMMENTS, ToVISIT,
            ToCATEGORY_MANAGE, ToUSERS_SETS, ToMANAGE_HEROES_INFO,
        )
    )
}

@Composable
fun Heroes(viewModel: HeroesViewModel = hiltViewModel()) {
    val heroes by viewModel.heroesFlow.collectAsState()
    ScreenView(title = "Выберите героя") {
        TagsWithRecycler(heroesTags(), heroes, { tag, hero ->
            tag?.text == hero.type || tag == null
        }) { hero -> HeroCard(hero) }
    }
    DisposableEffect(null) {
        viewModel.fetchHeroes()
        onDispose { viewModel.dispose() }
    }
}

@Composable
fun HeroCard(hero: Hero) {
    val navController = LocalNavController.current
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(stringToResource(hero.icon)),
                contentDescription = "heroIcon",
                modifier = Modifier.size(width = 120.dp, height = 150.dp)
            )
            Column(
                Modifier.padding(start = 10.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                OutlinedButton(text = "Сеты", marginStart = 10.dp, marginEnd = 10.dp) {
                    navController.navigate("${Destinations.SETS}/${hero.heroId}") { launchSingleTop = true }
                }
                OutlinedButton(text = "Топ 100", marginStart = 10.dp, marginEnd = 10.dp) {
                    navController.navigate("${Destinations.TOP}/${hero.heroId}") { launchSingleTop = true }
                }
            }
        }
    }
}
