package com.bulkapedia.compose.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.*

data class Tag(
    val id: Int,
    val text: String,
    val color: Color
)

@Composable
fun Tags(tags: List<Tag>, selected: MutableState<Int>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(tags) { tag ->
            TagItem(tag, selected.value == tag.id) {
                selected.value = if (selected.value == tag.id) -1 else tag.id
            }
        }
    }
}

@Composable
fun TagItem(tag: Tag, selected: Boolean, onClick: () -> Unit) {
    OutlinedCard(
        backgroundColor = if (selected) Teal200 else tag.color,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
            text = tag.text,
            color = if (selected) PrimaryDark else Teal200
        )
    }
}

fun heroesTags(): List<Tag> = fillDefaultTags(
    listOf("Дробовики", "Разведчики", "Снайперы", "Танки", "Штурмовики")
)

fun mapsTags(): List<Tag> = fillDefaultTags(
    listOf("Королевская битва", "Царь горы", "Стенка", "Саботаж")
)

private fun fillDefaultTags(labels: List<String>) : List<Tag> {
    val colors = List(labels.size) { PrimaryDark }
    return labels.mapIndexed { i, label ->
        Tag(id = i + 1, text = label, color = colors[i])
    }
}
