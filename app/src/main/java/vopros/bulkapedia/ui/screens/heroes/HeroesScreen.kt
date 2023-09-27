package vopros.bulkapedia.ui.screens.heroes

import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import vopros.bulkapedia.R
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.TagsWithRecycler
import vopros.bulkapedia.ui.components.tags.heroesTags
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.screens.Screen
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.utils.resourceManager

@Composable
fun HeroesScreen() {
    Screen<List<Hero>, HeroesViewModel>(
        R.string.select_hero, true,
        { startIntent(HeroesViewIntent.Start) }
    ) { _, heroes ->
        TagsWithRecycler(heroesTags(), heroes, { tag, hero ->
            tag?.id == hero.type || tag == null || tag.id.isEmpty()
        }) { HeroCard(hero = it) }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeroCard(hero: Hero) {
    val controller = LocalNavController.current
    Card(
        onClick = { controller.navigate("${Destinations.HERO}${hero.id}") },
    ) {
        HCenterBox {
            Text(text = stringResource(resourceManager.toSource(hero.id)))
        }
        Image(url = hero.image)
    }
}