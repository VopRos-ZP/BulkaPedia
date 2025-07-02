package ru.bulkapedia.presentation.ui.screens.heroes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.presentation.ui.components.BackTopAppBar
import ru.bulkapedia.presentation.ui.components.Loader

@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onHeroClick: (String) -> Unit,
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is HeroesSideEffect.OnBackClick -> onBackClick()
            is HeroesSideEffect.OnHeroClick -> onHeroClick(it.value)
        }
    }

    Scaffold(
        topBar = {
            BackTopAppBar("Выберите героя") {
                viewModel.onBackClick()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (state.isLoading) {
                Loader()
            } else {
                LazyVerticalGrid(
                    modifier = Modifier,
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(
                        items = state.heroes,
                        key = { it.id }
                    ) {
                        HeroCard(hero = it) {
                            viewModel.onHeroClick(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeroCard(
    hero: Hero,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = hero.imageUrl,
                contentDescription = hero.id,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}