@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.heroinfo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.data.category.HeroInfo
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.stringToResource

@Composable
fun HeroesInfoScreen(ctx: ToolbarCtx, viewModel: HeroesInfoViewModel) {
    // Toolbar
    ctx.observeAsState()
    ctx.setData(title = "Выберите персонажа", showBackButton = true)
    // Vars
    val viewState = viewModel.heroesData.observeAsState()
    // UI
    TextSnackbar {
        when (val state = viewState.value) {
            null -> CenteredBox { Text("Список пуст...", color = Teal200) }
            else -> {
                LazyVerticalGrid(
                    GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxSize()
                        .background(Primary)
                        .padding(horizontal = 20.dp)
                ) {
                    items(state, key = { it.id }) { info ->
                        HeroInfoCard(info) { id ->
                            ctx.navController.navigate(
                                route = "${Destinations.HERO_INFO}/$id"
                            )
                        }
                    }
                }
            }
        }
    }
    // Listener
    DisposableEffect(null) {
        viewModel.listenHeroInfo()
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun HeroInfoCard(
    heroInfo: HeroInfo,
    onClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(2.dp, Teal200),
        modifier = Modifier.width(200.dp).height(230.dp)
    ) {
        Image(
            painter = painterResource(stringToResource(heroInfo.hero)),
            contentDescription = heroInfo.hero,
            modifier = Modifier.fillMaxSize()
                .padding(10.dp)
                .clickable { onClick.invoke(heroInfo.id) }
        )
    }
}
