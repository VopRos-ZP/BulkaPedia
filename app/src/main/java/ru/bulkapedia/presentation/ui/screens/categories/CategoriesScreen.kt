package ru.bulkapedia.presentation.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.bulkapedia.domain.model.Category

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = koinViewModel(),
    onCategoryClick: (String) -> Unit,
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is CategoriesSideEffect.OnCategoryClick -> onCategoryClick(it.value)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.categories) {
                CategoryCard(it) {
                    viewModel.onCategoryClick(it)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = category.isEnable
    ) {
        Box(modifier = Modifier.padding(20.dp)) {
            Text(text = category.name)
        }
    }
}
