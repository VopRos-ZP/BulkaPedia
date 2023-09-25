package com.vopros.bulkapedia.ui.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.Text

@Composable
fun Tags(tags: List<Tag>, selected: MutableState<String>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(tags) { tag ->
            TagItem(tag, selected.value == tag.id) {
                selected.value = if (selected.value == tag.id) "" else tag.id
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagItem(tag: Tag, selected: Boolean, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(tag.text)
            if (selected) {
                Icon(Icons.Default.Check, contentDescription = null)
            }
        }
    }
}

fun heroesTags(): List<Tag> = fillDefaultTags(
    mapOf(
        "shortguns" to R.string.shortguns,
        "scouts" to R.string.scouts,
        "snipers" to R.string.snipers,
        "tanks" to R.string.tanks,
        "troopers" to R.string.troopers
    )
)

fun mapsTags(): List<Tag> = fillDefaultTags(
    mapOf(
        "battle_royale" to R.string.battle_royale_mode,
        "kings_of_the_hill" to R.string.kings_of_hill_mode,
        "squad" to R.string.squad_mode,
        "sabotage" to R.string.sabotage_mode
    )
)

private fun fillDefaultTags(labels: Map<String, Int>) : List<Tag> =
    labels.map { (key, label) -> Tag(key, label) }