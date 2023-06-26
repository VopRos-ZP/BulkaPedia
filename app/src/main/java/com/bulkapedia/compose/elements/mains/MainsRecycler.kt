package com.bulkapedia.compose.elements.mains

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.data.repos.stats.Stats
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun MainsRecycler(
    mains: SnapshotStateList<Stats>,
    selected: MutableState<Stats?>,
    onItemClick: (Stats) -> Unit
) {
    LazyRow (
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(mains) { stats ->
            StatsItem(stats, selected.value == stats) {
                selected.value = if (selected.value == stats) null else stats
                onItemClick(stats)
            }
        }
    }
}

@Composable
fun StatsItem(stats: Stats, selected: Boolean, onClick: () -> Unit) {
    OutlinedCard(
        backgroundColor = if (selected) Teal200 else PrimaryDark,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
            text = stats.id.split(" ", limit = 2).last(),
            color = if (selected) PrimaryDark else Teal200
        )
    }
}
