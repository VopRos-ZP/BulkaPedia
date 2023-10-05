package vopros.bulkapedia.ui.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.cards.Card

@Composable
fun Tags(tags: List<Tag>, selected: Tag?, onSelect: (Tag) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(tags) { tag ->
            TagItem(tag, selected == tag) { onSelect(tag) }
        }
    }
}

@Composable
fun TagItem(tag: Tag, selected: Boolean, onClick: () -> Unit) {
    Card(onClick = onClick, radius = 10.dp) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(tag.text)
            if (selected) {
                Icon(
                    Icons.Outlined.Close,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
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