package ru.bulkapedia.presentation.ui.screens.sets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.model.HeroType
import ru.bulkapedia.domain.utils.Utils
import ru.bulkapedia.presentation.ui.alert.WithAlert
import ru.bulkapedia.presentation.ui.components.ScaffoldToolbar

@Composable
fun SetsScreen(
    navController: NavController,
    viewModel: SetsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ScaffoldToolbar(text = "Сеты", navController) {
        WithAlert(message = state.error, onClose = viewModel::closeError)
        Column(modifier = Modifier.fillMaxSize()) {
            val tags = HeroType.entries.toList()
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(tags) { type ->
                    val isSelected = state.selectedHeroType == type
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.filterType(if (isSelected) null else type) },
                        label = { Text(text = stringResource(Utils.resourceManager.toSource(type.name.lowercase()))) }
                    )
                }
            }
            val heroes = state.selectedHeroType?.let { type -> state.heroes.filter { m -> m.type == type } } ?: state.heroes
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(heroes) { hero ->
                    HeroCard(hero) {}
                }
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchHeroes()
    }
}

@Composable
fun HeroCard(hero: Hero, onClick: () ->  Unit) {
    Card(onClick = onClick) {
        AsyncImage(
            model = hero.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().padding(top = 5.dp)
        )
    }
}