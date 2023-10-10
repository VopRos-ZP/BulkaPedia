package vopros.bulkapedia.ui.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.button.OutlinedButton
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.ui.theme.BulkapediaTheme

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

@Preview(showBackground = true)
@Composable
fun Tag_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark, modifier = Modifier.fillMaxWidth()) {
            TagItem(tag = Tag("shortguns", R.string.shortguns), selected = false) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Tag_Selected_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark, modifier = Modifier.fillMaxWidth()) {
            TagItem(tag = Tag("shortguns", R.string.shortguns), selected = true) {}
        }
    }
}

@Composable
fun TagItem(tag: Tag, selected: Boolean, onClick: () -> Unit) {
    val (textColor, bgColor) = when (selected) {
        true -> Pair(BulkaPediaTheme.colors.primaryDark, BulkaPediaTheme.colors.tintColor)
        else -> Pair(BulkaPediaTheme.colors.tintColor, BulkaPediaTheme.colors.primary)
    }
    Card(
        onClick = onClick,
        radius = 10.dp,
        color = bgColor
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(tag.text, color = textColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinedTag_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark, modifier = Modifier.fillMaxWidth()) {
            OutlinedTagItem(tag = Tag("shortguns", R.string.shortguns), selected = false) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinedTag_Selected_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark, modifier = Modifier.fillMaxWidth()) {
            OutlinedTagItem(tag = Tag("shortguns", R.string.shortguns), selected = true) {}
        }
    }
}

@Composable
fun OutlinedTagItem(tag: Tag, selected: Boolean, onClick: () -> Unit) {
    val (textColor, bgColor) = when (selected) {
        true -> Pair(BulkaPediaTheme.colors.white, BulkaPediaTheme.colors.tintColor)
        else -> Pair(BulkaPediaTheme.colors.tintColor, Color.Transparent)
    }
    OutlinedButton(
        onClick = onClick,
        bgColor = bgColor
    ) {
        Text(tag.text, color = textColor)
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