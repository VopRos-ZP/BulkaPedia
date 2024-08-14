package ru.bulkapedia.presentation.screens.sets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ru.bulkapedia.R
import ru.bulkapedia.domain.model.Fraction
import ru.bulkapedia.domain.model.hero.Hero
import ru.bulkapedia.presentation.components.topbar.BackTopBar
import ru.bulkapedia.presentation.navigation.Screen

@Composable
fun SetsScreen(
    onClick: () -> Unit,
    onNavigate: (Screen) -> Unit,
    viewModel: SetsViewModel = koinViewModel()
) {
    val state by viewModel.viewStates().collectAsState()
    Scaffold(
        topBar = { BackTopBar(onClick = onClick, text = R.string.select_hero) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(state.heroes) {
                    HeroItem(
                        hero = it,
                        fraction = state.fractions.find { f -> f.id == it.fraction },
                        onClick = {}
                    )
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.intent(SetsViewIntent.Launch)
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    fraction: Fraction?,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        enabled = hero.isActive,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            if (fraction != null) {
                AsyncImage(
                    modifier = Modifier.size(40.dp).padding(start = 5.dp, top = 5.dp),
                    model = fraction.imageUrl,
                    contentDescription = fraction.id
                )
            }
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                model = hero.imageUrl,
                contentDescription = hero.id
            )
        }
    }
}
