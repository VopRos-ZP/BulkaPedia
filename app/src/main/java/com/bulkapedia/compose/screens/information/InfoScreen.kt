@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.information

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.data.*
import com.bulkapedia.compose.data.category.Category
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.clickable
import kotlinx.coroutines.launch

@Composable
fun InfoListNav(toolbarCtx: ToolbarCtx) {
    Navigation(Destinations.INFO, toolbarCtx, listOf(
        ToINFO, ToDEV_CHAT, ToSETTINGS, ToDASHBOARD,
        /** Maps category **/
        ToMAPS, ToMAP,
        /** HeroInfo category **/
        ToHEROES_INFO, ToHERO_INFO,
        /** Mechanics category **/
        // soon
    ))
}

@Composable
fun InfoScreen(ctx: ToolbarCtx, viewModel: InfoViewModel) {
    // Toolbar
    ctx.observeAsState()
    ctx.setData(title = "Выберите категорию", showBackButton = false)
    // Vars
    val scope = rememberCoroutineScope()
    val categoryState = viewModel.categoryLiveData.observeAsState()
    // UI
    TextSnackbar { action ->
        LazyColumn (
            modifier = Modifier.fillMaxSize()
                .background(Primary)
        ) {
            items(categoryState.value ?: emptyList()) { cat ->
                Category(cat, (categoryState.value ?: emptyList())
                    .first() == cat
                ) { destination ->
                    if (cat.enabled) {
                        ctx.navController.navigate(destination)
                    } else {
                        scope.launch { action.showSnackbar("Скоро откроется") }
                    }
                }
            }
        }
    }
    // Listeners
    DisposableEffect(null) {
        viewModel.listenCategories()
        onDispose { viewModel.removeListeners() }
    }
}

@Composable
fun Category(
    category: Category,
    isFirst: Boolean,
    onClick: (String) -> Unit
) {
    val top = if (isFirst) 0.dp else 20.dp
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(2.dp, Teal200),
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = top)
    ) {
        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
                .clickable { onClick.invoke(category.destination) },
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = category.title,
                color = Teal200,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp)
            )
            Text(
                text = category.subTitle,
                color = Teal,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}
