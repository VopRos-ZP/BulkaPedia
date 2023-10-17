package vopros.bulkapedia.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import vopros.bulkapedia.R
import vopros.bulkapedia.category.Category
import vopros.bulkapedia.ui.components.CenterBox
import vopros.bulkapedia.ui.components.Loading
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.ui.screens.destinations.HeroesScreenDestination
import vopros.bulkapedia.ui.screens.destinations.MapsScreenDestination
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.utils.iconNameToVector

@RootNavGraph(start = true)
@Destination
@Composable
fun CategoriesScreen(navigator: DestinationsNavigator, viewModel: CategoriesViewModel) {
    val categories by viewModel.categories.collectAsState()
    ScreenView(
        title = R.string.wiki,
        viewModel = viewModel,
        fetch = { fetchCategories() }
    ) {
        when (val cs = categories) {
            null -> Loading()
            emptyList<Category>() -> CenterBox {
                Text(resource = R.string.empty_sets)
            }
            else -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(cs) { CategoryCard(it) {
                    navigator.navigate(when (it.destination) {
                        "maps" -> MapsScreenDestination()
                        "heroes" -> HeroesScreenDestination()
                        else -> throw RuntimeException()
                    })
                } }
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Card(onClick = onClick) {
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
                Icon(
                    iconNameToVector(category.icon),
                    contentDescription = null,
                    tint = BulkaPediaTheme.colors.tintColor
                )
                Text(title = category.title, fontWeight = FontWeight.Bold)
            }
            Text(title = category.subTitle, fontSize = 12.sp)
        }
    }
}