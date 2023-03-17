@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.compose.data.sets.UserSet

@Composable
fun SetsRecycler(sets: List<UserSet>, onDelete: (UserSet) -> Unit) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth()
    ) {
        items(sets) {set ->
            SetTabCard(set, see = false, onDelete = onDelete)
        }
    }
}
