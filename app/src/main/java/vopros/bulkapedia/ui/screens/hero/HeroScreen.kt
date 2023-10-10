package vopros.bulkapedia.ui.screens.hero

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
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Loading
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.ui.components.tab.Tab
import vopros.bulkapedia.ui.components.tab.TabRowWithPager
import vopros.bulkapedia.ui.components.userSet.UserSetCard
import vopros.bulkapedia.ui.theme.Blue
import vopros.bulkapedia.utils.resourceManager

@Destination
@Composable
fun HeroScreen(
    navigator: DestinationsNavigator,
    viewModel: HeroViewModel,
    heroId: String
) {
    val hero by viewModel.hero.collectAsState()
    val sets by viewModel.sets.collectAsState()
    ScreenView(
        title = resourceManager.toSource(heroId),
        showBack = true,
        viewModel = viewModel
    ) {
        when (hero) {
            null -> Loading()
            else -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    /* Hero icon with difficult */
                    HeroThumbnail(hero!!.image) {
                        Text(
                            R.string.difficult,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        HeroDifficult(difficult = hero!!.difficult)
                    }

                    /* UserSets */
                    TabRowWithPager(
                        listOf(Tab(R.string.one), Tab(R.string.two), Tab(R.string.three)), sets
                    ) { HCenterBox { UserSetCard(it) } }

                    /* Add user set Button */
                    OutlinedButton(onClick = {
                        
                    }) { Text(R.string.create_set) }
                }
            }
        }
    }
    DisposableEffect(heroId) {
        viewModel.fetch(heroId)
        onDispose { viewModel.dispose() }
    }
}

@Composable
fun HeroThumbnail(
    image: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(url = image, modifier = Modifier.size(150.dp))
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
        "easy" -> listOf(Blue, Color.Transparent, Color.Transparent)
        "normal" -> listOf(Blue, Blue, Color.Transparent)
        "hard" -> listOf(Blue, Blue, Blue)
        else -> emptyList()
    }
}
