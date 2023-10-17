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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import vopros.bulkapedia.R
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.ui.components.CenterBox
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Loading
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.ui.components.tags.Tag
import vopros.bulkapedia.ui.components.tags.Tags
import vopros.bulkapedia.ui.components.tags.heroesTags
import vopros.bulkapedia.ui.screens.destinations.HeroScreenDestination
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.utils.resourceManager

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun HeroesScreen(navigator: DestinationsNavigator, viewModel: HeroesViewModel) {
    val tags = heroesTags()
    val heroes by viewModel.heroes.collectAsState()
    var selectedTag by remember { mutableStateOf<Tag?>(null) }
    ScreenView(
        title = R.string.select_hero,
        showBack = true,
        viewModel = viewModel,
        key = selectedTag,
        fetch = { fetchHeroes(selectedTag) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Tags(tags, selectedTag) { selectedTag = if (selectedTag == it) null else it }
            when (val hs = heroes) {
                null -> Loading()
                emptyList<Hero>() -> CenterBox { Text(resource = R.string.empty_sets) }
                else -> LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(hs) { HeroCard(Modifier.animateItemPlacement(), it) {
                        navigator.navigate(HeroScreenDestination(it.id))
                    } }
                }
            }
        }
    }
}

@Composable
fun HeroCard(modifier: Modifier = Modifier, hero: Hero, onClick: () -> Unit) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(resource = resourceManager.toSource(hero.id), color = BulkaPediaTheme.colors.white, fontWeight = FontWeight.Bold)
        Image(url = hero.image)
    }
}