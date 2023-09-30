package vopros.bulkapedia.ui.screens.categories

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.R
import vopros.bulkapedia.category.Category
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.cards.OutlinedCard
import vopros.bulkapedia.ui.screens.Error
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.utils.iconNameToVector

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = hiltViewModel()) {
    val categories by viewModel.categories.collectAsState()
    ScreenView(
        title = R.string.wiki,
        viewModel = viewModel
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(categories) { CategoryCard(it) }
        }
    }
    LaunchedEffect(null) { viewModel.fetchCategories() }
}

@Composable
fun CategoryCard(category: Category) {
    val controller = LocalNavController.current
    OutlinedCard(
        onClick = {
            if (category.enabled) {
                controller.navigate(category.destination) { launchSingleTop = true }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(iconNameToVector(category.icon), contentDescription = null)
                Text(text = category.title, fontWeight = FontWeight.Bold)
            }
            Text(text = category.subTitle, fontSize = 12.sp)
        }
    }
}