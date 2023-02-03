package com.bulkapedia.compose.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.models.TagViewModel
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.R
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.clickable

data class Tag(
    val id: Int,
    val text: String,
    val color: Color
)

@Composable
fun Tags(tags: List<Tag>, tagViewModel: TagViewModel) {
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        tags.map { tag ->
            item { TagItem(
                tag = tag,
                viewModel = tagViewModel,
                isStart = tags.first().id == tag.id,
                isEnd = tags.last().id == tag.id
            ) }
        }
    }
}

@Composable
fun TagItem(tag: Tag, viewModel: TagViewModel, isStart: Boolean = false, isEnd: Boolean = false) {
    val viewState = viewModel.viewState.observeAsState()
    val isTabSelected = viewState.value?.selected == true && viewState.value?.tagId == tag.id
    val padding = if (isStart) PaddingValues(start = 20.dp, end = 5.dp)
        else if (isEnd) PaddingValues(start = 5.dp, end = 20.dp)
        else PaddingValues(horizontal = 5.dp)
    VCenteredBox (modifier = Modifier.padding(padding)) {
        Row(
            modifier = Modifier
                .background(
                    color = if (isTabSelected) Teal200 else tag.color,
                    shape = RoundedCornerShape(20.dp)
                )
                .border(2.dp, Teal200, RoundedCornerShape(50.dp))
                .clickable { viewModel.toggle(tag, viewState.value) }
                .padding(5.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = tag.text,
                color = if (isTabSelected) PrimaryDark else Teal200
            )
        }
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

@Preview(showBackground = true)
@Composable
fun Tags_Preview() {
    Tags(tags = heroesTags(), tagViewModel = hiltViewModel())
}
