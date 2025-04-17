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
import coil.compose.AsyncImage
import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.model.HeroType
import ru.bulkapedia.domain.utils.Utils
import ru.bulkapedia.presentation.ui.alert.WithAlert
import ru.bulkapedia.presentation.ui.components.ScaffoldToolbar

@Composable
fun SetsScreen(
) {

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