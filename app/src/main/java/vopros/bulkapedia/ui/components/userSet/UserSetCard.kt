package vopros.bulkapedia.ui.components.userSet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.ui.components.IconButton
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.userSet.UserSetUseCase

@Composable
fun UserSetCard(
    container: UserSetUseCase,
    withHeroIcon: Boolean = false
) {
    var expand by remember { mutableStateOf(false) }
    val viewModel: UserSetCardViewModel = hiltViewModel()
    LaunchedEffect(container.set) { viewModel.fetchConfig() }
    val config by viewModel.config.collectAsState()
    UserSetCard(
        gears = container.set.gears,
        up = { AnimatedVisibility(expand && withHeroIcon) { Image(url = container.hero.image) } }
    ) {
        Text(title = container.user.nickname)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(title = "${container.set.liked.size}")
            IconButton(
                onClick = { viewModel.like(container) },
                tint = Color.Red,
                icon = if (container.set.liked.contains(config?.first)) Icons.Default.Favorite
                else Icons.Default.FavoriteBorder
            )
        }
        Row {
            IconButton(onClick = { /* settings click */ }, icon = Icons.Default.Settings)
            IconButton(onClick = { /* comment click */ }, icon = Icons.Default.Comment)
            if (withHeroIcon) {
                IconToggleButton(
                    checked = expand,
                    onCheckedChange = { expand = it },
                ) {
                    Icon(
                        if (expand) Icons.Default.HideImage
                        else Icons.Default.Image,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun UserSetCard(
    gears: Map<String, String>,
    up: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Card(radius = 10.dp) {
        Column(modifier = Modifier.padding(15.dp)) {
            up()
            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                Gears(gears = gears)
                Column(
                    Modifier.height((75 * 3).dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = content
                )
            }
        }
    }
}

@Composable
fun Gears(gears: Map<String, String>, onClick: (String) -> Unit = {}) {
    Column(modifier = Modifier.height((75 * 3).dp)) {
        GearRow(gears = listOf(gears["head"]!!, gears["body"]!!), onClick)
        GearRow(gears = listOf(gears["arm"]!!, gears["leg"]!!), onClick)
        GearRow(gears = listOf(gears["decor"]!!, gears["device"]!!), onClick)
    }
}

@Composable
private fun GearRow(gears: List<String>, onClick: (String) -> Unit) {
    Row(modifier = Modifier.width((75 * 2).dp)) {
        gears.map { value ->
            Image(
                url = value,
                modifier = Modifier
                    .size(75.dp)
                    .clickable { onClick(value) }
            )
        }
    }
}