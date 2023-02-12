@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.clickable

data class DashboardItem(val text: String, val onClick: () -> Unit)

@Composable
fun DashboardScreen(ctx: ToolbarCtx) {
    ctx.observeAsState()
    ctx.setData("Доска управления", true)
    // Vars
    val items = listOf(
        DashboardItem("Юзеры\nи Сеты") { ctx.navController.navigate(Destinations.USERS_SETS) },
        DashboardItem("Категории\nWiki") { ctx.navController.navigate(Destinations.CATEGORY_MANAGE) },
        DashboardItem("Добавить\nheroInfo") { ctx.navController.navigate("${Destinations.DASHBOARD}/${Destinations.HERO_INFO}") },
    )
    // UI
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(fraction = 0.923f)
            .background(Primary)
            .padding(20.dp)
    ) {
        items(items) { item -> DashboardItemCard(item) }
    }
}

@Composable
fun DashboardItemCard(item: DashboardItem) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(1.dp, Teal200),
        modifier = Modifier.clickable(item.onClick)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item.text,
                color = Teal200, textAlign = TextAlign.Center,
                fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}
