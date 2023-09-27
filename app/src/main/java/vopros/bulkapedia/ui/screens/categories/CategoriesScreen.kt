package vopros.bulkapedia.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vopros.bulkapedia.R
import vopros.bulkapedia.category.Category
import vopros.bulkapedia.ui.screens.Screen
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.utils.iconNameToVector

@Composable
fun CategoriesScreen() {
    Screen<List<Category>, CategoriesViewModel>(
        title = R.string.wiki,
        fetch = { startIntent(CategoriesViewIntent.Start) }
    ) { _, categories ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(categories) { CategoryCard(it) }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryCard(category: Category) {
    val controller = LocalNavController.current
    Card(
        modifier = Modifier.fillMaxSize(0.9f),
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