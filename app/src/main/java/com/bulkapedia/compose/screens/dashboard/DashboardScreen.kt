package com.bulkapedia.compose.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal200

data class DashboardItem(val text: String, val onClick: () -> Unit)

@Composable
fun DashboardScreen() {
    val navController = LocalNavController.current
    val items = listOf(
        DashboardItem("Юзеры\nи Сеты") { navController.navigate(Destinations.USERS_SETS) },
        DashboardItem("Категории\nWiki") { navController.navigate(Destinations.CATEGORY_MANAGE) },
        DashboardItem("Добавить\nheroInfo") { navController.navigate("${Destinations.DASHBOARD}/${Destinations.HERO_INFO}") },
    )
    ScreenView(title = "Доска управления", showBack = true) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .padding(20.dp)
        ) {
            items(items) { item -> DashboardItemCard(item) }
        }
    }
}

@Composable
fun DashboardItemCard(item: DashboardItem) {
    OutlinedCard(onClick = item.onClick) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item.text,
                color = Teal200, textAlign = TextAlign.Center,
                fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}
