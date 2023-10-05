package vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.ui.components.tags.Tag
import vopros.bulkapedia.ui.components.tags.Tags
import vopros.bulkapedia.ui.screens.Loading

@Composable
fun <T> TagsWithRecycler(
    tags: List<Tag>,
    list: List<T>,
    filter: (Tag?, T) -> Boolean,
    itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    val selectedTag = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Tags(tags, selectedTag)
        when (list.isEmpty()) {
            true -> Loading()
            else -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    list.filter { t -> filter(tags.find { tag -> tag.id == selectedTag.value }, t) },
                    itemContent = itemContent
                )
            }
        }
    }
}