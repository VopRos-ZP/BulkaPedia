package ru.bulkapedia.presentation.ui.screens.heroDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.bulkapedia.domain.utils.ResourceManager
import ru.bulkapedia.domain.utils.Utils.resourceManager
import ru.bulkapedia.presentation.ui.components.BackTopAppBar

@Composable
fun HeroDetailsScreen(
    viewModel: HeroDetailsViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is HeroDetailsSideEffect.OnBackClick -> onBackClick()
        }
    }

    Scaffold(
        topBar = {
            BackTopAppBar(stringResource(resourceManager.toSource(state.id))) {
                viewModel.onBackClick()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = state.imageUrl,
                contentDescription = state.id
            )
        }
    }
}