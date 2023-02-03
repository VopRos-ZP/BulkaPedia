@file:Suppress("FunctionName")

package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.data.sets.UserSet

@Composable
fun SetsRecycler(sets: List<UserSet>, onDelete: (UserSet) -> Unit) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth()
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .border(2.dp, Teal200, RoundedCornerShape(20.dp))
    ) {
        items(sets) {set ->
            SetTabCard(set, see = false, onDelete = onDelete)
        }
    }
}
