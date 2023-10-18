package vopros.bulkapedia.ui.screens.hero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Loading
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.button.OutlinedButton
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.ui.components.tab.Tab
import vopros.bulkapedia.ui.components.tab.TabRowWithPager
import vopros.bulkapedia.ui.components.userSet.UserSetCard
import vopros.bulkapedia.ui.screens.destinations.CommentsScreenDestination
import vopros.bulkapedia.ui.screens.destinations.CreateSetScreenDestination
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
        viewModel = viewModel,
        fetch = { fetch(heroId) }
    ) {
        when (hero) {
            null -> Loading()
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(vertical = 20.dp)
                ) {
                    /* Hero icon with difficult */
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Image(url = hero!!.image, modifier = Modifier.height(150.dp))
                        }
                    }
                    /* UserSets */
                    item {
                        TabRowWithPager(
                            listOf(Tab(R.string.one), Tab(R.string.two), Tab(R.string.three)), sets
                        ) { HCenterBox { UserSetCard(it) {
                            navigator.navigate(CommentsScreenDestination(it.set.id))
                        } } }
                    }
                    /* Add user set Button */
                    item {
                        OutlinedButton(onClick = {
                            navigator.navigate(CreateSetScreenDestination(heroId, null))
                        }, text = R.string.create_set)
                    }
                }
            }
        }
    }
}

@Composable
fun HeroThumbnail(
    image: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
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
