package vopros.bulkapedia.ui.screens.createSet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Loading
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.screens.hero.HeroThumbnail

@Destination
@Composable
fun CreateSetScreen(
    viewModel: CreateSetViewModel,
    heroId: String,
    setId: String?
) {
    val useCase by viewModel.useCase.collectAsState()
    ScreenView(
        title = R.string.create_set,
        showBack = true,
        viewModel = viewModel,
        fetch = { init(heroId, setId) }
    ) {
        when (val case = useCase) {
            null -> Loading()
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        HeroThumbnail(image = case.hero.image) {
                            OutlinedButton(onClick = { viewModel.saveSet(case.set) }) {
                                Text(R.string.save)
                            }
                        }
                    }
                }
            }
        }
    }
}