package vopros.bulkapedia.ui.screens.heroes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.R
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.ui.components.tags.Tag
import vopros.bulkapedia.ui.components.tags.Tags
import vopros.bulkapedia.ui.components.tags.heroesTags
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.theme.LocalNavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesScreen(viewModel: HeroesViewModel = hiltViewModel()) {
    val tags = heroesTags()
    val heroes by viewModel.heroes.collectAsState()
    var selectedTag by remember { mutableStateOf<Tag?>(null) }
    ScreenView(
        title = R.string.select_hero,
        showBack = true,
        viewModel = viewModel
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Tags(tags, selectedTag) { selectedTag = if (selectedTag == it) null else it }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(heroes) { HeroCard(Modifier.animateItemPlacement(), it) }
            }
        }
    }
    LaunchedEffect(selectedTag) {
        viewModel.fetchHeroes(selectedTag)
    }
}

@Composable
fun HeroCard(modifier: Modifier = Modifier, hero: Hero) {
    val controller = LocalNavController.current
    Card(
        modifier = modifier,
        onClick = { controller.navigate("${Destinations.HERO}${hero.id}") }
    ) {
        Image(url = hero.image)
    }
}