package com.vopros.bulkapedia.ui.screens.createSet

import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.userSet.Gears
import com.vopros.bulkapedia.ui.screens.Screen
import com.vopros.bulkapedia.ui.screens.hero.HeroThumbnail
import com.vopros.bulkapedia.userSet.UserSetUseCase

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