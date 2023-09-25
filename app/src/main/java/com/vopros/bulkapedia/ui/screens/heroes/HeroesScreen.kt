package com.vopros.bulkapedia.ui.screens.heroes

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.ui.components.TagsWithRecycler
import com.vopros.bulkapedia.ui.components.tags.heroesTags
import com.vopros.bulkapedia.ui.navigation.Destinations
import com.vopros.bulkapedia.ui.screens.Screen
import com.vopros.bulkapedia.ui.theme.LocalNavController
import com.vopros.bulkapedia.utils.resourceManager

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

@OptIn(ExperimentalMaterial3Api::class)
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