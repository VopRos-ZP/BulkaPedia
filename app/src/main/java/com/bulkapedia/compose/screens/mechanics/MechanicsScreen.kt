@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.mechanics

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import com.bulkapedia.compose.data.category.Mechanic
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun MechanicsScreen(ctx: ToolbarCtx, viewModel: MechanicsViewModel) {
    ctx.observeAsState()
    ctx.setData(title = "Механики игры", showBackButton = true)
    // Vars
    // UI
    val viewState = viewModel.mechanicsData.observeAsState()
    when (val state = viewState.value!!) {
        emptyList<Mechanic>() -> CenteredBox { Text(text = "Список пуст...", color = Teal200) }
        else -> {
            LazyColumn {
                items(state) { m ->
                    MechanicsItem(m) { id ->
                        ctx.navController.navigate("${Destinations.MECHANICS}/$id")
                    }
                }
            }
        }
    }
    // Listener
    DisposableEffect(null) {
        viewModel.listenMechanics()
        onDispose { viewModel.removeListener() }
    }
}
