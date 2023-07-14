package com.bulkapedia.compose.screens.dashboard.screens.categorymanage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import bulkapedia.categories.Category
import com.bulkapedia.compose.elements.LoginBlock
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun CategoryManageScreen(viewModel: CategoryManageViewModel = hiltViewModel()) {
    val categories by viewModel.categoriesFlow.collectAsState()
    ScreenView(title = "Категории Wiki", showBack = true) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(top = 20.dp)
                .background(Primary)
        ) {
            LoginBlock {
                categories.map { CategoryManageItem(it, viewModel::updateCategory) }
            }
        }
    }
    // Listeners
    DisposableEffect(null) {
        viewModel.listenCategories()
        onDispose { viewModel.removeCategory() }
    }
}

@Composable
fun CategoryManageItem(category: Category, onCheckChange: (Category) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = category.title, color = Teal200, fontSize = 16.sp)
        Row {
            Switch(
                category.enabled,
                onCheckedChange = {
                    val newCat = category.copy(enabled = it)
                    onCheckChange.invoke(newCat)
                }
            )
            /** Soon add change title and subTitle **/
        }
    }
}
