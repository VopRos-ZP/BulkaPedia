package vopros.bulkapedia.ui.screens.createSet

import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.userSet.Gears
import vopros.bulkapedia.ui.screens.Screen
import vopros.bulkapedia.ui.screens.hero.HeroThumbnail
import vopros.bulkapedia.userSet.UserSetUseCase

@Composable
fun CreateSetScreen(heroId: String, setId: String?) {
    Screen<UserSetUseCase, CreateSetViewModel>(
        title = R.string.create_set,
        showBack = true,
        fetch = { startIntent(CreateSetViewIntent.Start(heroId, setId)) }
    ) { _, useCase ->
        HeroThumbnail(image = useCase.hero.image) {
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(R.string.save)
            }
        }
        Card {
            Gears(useCase.set.gears)
        }
    }
}