package vopros.bulkapedia.ui.screens.heroes

import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.R
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.TagsWithRecycler
import vopros.bulkapedia.ui.components.cards.OutlinedCard
import vopros.bulkapedia.ui.components.tags.heroesTags
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.utils.resourceManager

@Composable
fun HeroesScreen(viewModel: HeroesViewModel = hiltViewModel()) {
    val heroes by viewModel.heroes.collectAsState()
    ScreenView(
        title = R.string.select_hero,
        showBack = true,
        viewModel = viewModel
    ) {
        TagsWithRecycler(heroesTags(), heroes, { tag, hero ->
            tag?.id == hero.type || tag == null || tag.id.isEmpty()
        }) { HeroCard(hero = it) }
    }
    LaunchedEffect(null) { viewModel.fetchHeroes() }
}

@Composable
fun HeroCard(hero: Hero) {
    val controller = LocalNavController.current
    OutlinedCard(
        onClick = { controller.navigate("${Destinations.HERO}${hero.id}") }
    ) {
        HCenterBox {
            Text(text = stringResource(resourceManager.toSource(hero.id)))
        }
        Image(url = hero.image, modifier = Modifier.size(434.dp, 447.dp))
    }
}