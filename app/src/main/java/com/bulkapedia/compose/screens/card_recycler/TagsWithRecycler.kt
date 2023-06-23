package com.bulkapedia.compose.screens.card_recycler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bulkapedia.compose.elements.BorderedLazyColumnH20
import com.bulkapedia.compose.elements.Tag
import com.bulkapedia.compose.elements.Tags
import com.bulkapedia.compose.screens.Loading

@Composable
fun <T> TagsWithRecycler(
    tags: List<Tag>,
    list: List<T>,
    filter: (Tag?, T) -> Boolean,
    itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    val selectedTag = remember { mutableStateOf(-1) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Tags(tags, selectedTag)
        when (list.isEmpty()) {
            true -> Loading()
            else -> BorderedLazyColumnH20 {
                items(
                    list.filter { t -> filter(tags.find { tag -> tag.id == selectedTag.value }, t) },
                    itemContent = itemContent
                )
            }
        }
    }
}
