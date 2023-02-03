package com.bulkapedia.compose.screens.top

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.bulkapedia.compose.data.sets.UserSet

sealed class TopEvent {
    data class LoadingSets(val hero: String, val state: SnapshotStateList<UserSet>): TopEvent()
}
