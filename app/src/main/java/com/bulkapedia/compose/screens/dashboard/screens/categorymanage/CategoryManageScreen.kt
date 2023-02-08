@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.dashboard.screens.categorymanage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.data.category.Category
import com.bulkapedia.compose.elements.LoginBlock
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun CategoryManageScreen(ctx: ToolbarCtx, viewModel: CategoryManageViewModel) {
    ctx.observeAsState()
    ctx.setData(title = "", showBackButton = true)
    // UI
    val viewState = viewModel.liveData.observeAsState()
    when (val state = viewState.value!!) {
        emptyList<Category>() -> {
            CenteredBox (modifier = Modifier.fillMaxSize().background(Primary)) {
                Text(text = "Список пуст...", color = Teal200)
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(fraction = 0.923f)
                    .background(Primary)
            ) {
                LoginBlock {
                    state.map { cat ->
                        CategoryManageItem(cat, viewModel::updateCategory)
                    }
                }
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
        modifier = Modifier.fillMaxWidth()
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
