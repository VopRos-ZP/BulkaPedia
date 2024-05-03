package ru.bulkapedia.presentation.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.presentation.ui.screens.categories.component.CategoriesComponent

@Composable
fun CategoriesScreen(component: CategoriesComponent) {
    val state by component.state.collectAsState()
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
                CategoryCard(it) { component.onCategoryClick(it) }
            }
        }
        if (state.error != null) {
            AlertDialog(
                onDismissRequest = {/* On click outside */},
                text = { Text(text = state.error!!) },
                confirmButton = {
                    Button(onClick = { component.onCloseError() }) {
                        Text(text = "Закрыть")
                    }
                }
            )
        }
    }
}

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Box(modifier = Modifier.padding(20.dp)) {
            Text(text = category.title)
        }
    }
}
