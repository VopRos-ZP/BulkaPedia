package com.vopros.bulkapedia.ui.screens.hero

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.tab.Tab
import com.vopros.bulkapedia.ui.components.tab.TabRowWithPager
import com.vopros.bulkapedia.ui.components.userSet.UserSetCard
import com.vopros.bulkapedia.ui.navigation.Destinations
import com.vopros.bulkapedia.ui.screens.Screen
import com.vopros.bulkapedia.ui.theme.LocalNavController
import com.vopros.bulkapedia.userSet.UserSetUseCase
import com.vopros.bulkapedia.utils.resourceManager

@Composable
fun HeroScreen(heroId: String) {
    val controller = LocalNavController.current
    var title by remember { mutableIntStateOf(R.string.select_hero) }
    Screen<Pair<Hero, List<UserSetUseCase>>, HeroViewModel>(
        title = title, showBack = true,
        fetch = { startIntent(HeroViewIntent.Fetch(heroId)) },
        dispose = { startIntent(HeroViewIntent.Dispose) }
    ) { _, (hero, sets) ->
        title = resourceManager.toSource(hero.id)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            /* Hero icon with difficult */
            HeroThumbnail(hero.image) {
                Text(
                    R.string.difficult,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                HeroDifficult(difficult = hero.difficult)
            }

            /* UserSets */
            TabRowWithPager(
                listOf(Tab(R.string.one), Tab(R.string.two), Tab(R.string.three)), sets
            ) { HCenterBox { UserSetCard(it) } }

            /* Add user set Button */
            OutlinedButton(onClick = {
                controller.navigate("${Destinations.CREATE_SET}/${hero.id}")
            }) { Text(R.string.create_set) }

        }
    }
}

@Composable
fun HeroThumbnail(
    image: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(url = image)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                content = content
            )
        }
    }
}

@Composable
fun HeroDifficult(difficult: String) {
    val colors = getDifficultImages(difficult)
    Row(modifier = Modifier.background(Color.Transparent)) {
        colors.map {
            Box(modifier = Modifier
                .padding(end = 10.dp)
                .size(width = 50.dp, height = 15.dp)
                .background(it, RectangleShape)
                .border(1.dp, Color.Black)
            )
        }
    }
}

private fun getDifficultImages(difficult: String): List<Color> {
    return when (difficult) {
        "easy" -> listOf(Color.Yellow, Color.Transparent, Color.Transparent)
        "normal" -> listOf(Color.Yellow, Color.Yellow, Color.Transparent)
        "hard" -> listOf(Color.Yellow, Color.Yellow, Color.Yellow)
        else -> emptyList()
    }
}
