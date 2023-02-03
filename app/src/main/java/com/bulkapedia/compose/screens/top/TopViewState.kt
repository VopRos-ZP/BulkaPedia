package com.bulkapedia.compose.screens.top

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.bulkapedia.compose.data.sets.UserSet

sealed class TopViewState {
    object Loading: TopViewState()
    data class EnterSets(val sets: SnapshotStateList<UserSet>): TopViewState()
    data class Error(val message: String): TopViewState()
}