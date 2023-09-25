package com.vopros.bulkapedia.ui.components.userSet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.userSet.UserSetWithUser

@Composable
fun UserSetCard(userSet: UserSetWithUser) {
    Card {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Gears(gears = userSet.gears)
            Column(
                modifier = Modifier.height((75 * 3 + 5 * 2).dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = userSet.author.nickname)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${userSet.liked.size}")
                    IconButton(onClick = { /* like click */ }) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
                Row {
                    IconButton(onClick = { /* settings click */ }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /* comment click */ }) {
                        Icon(
                            Icons.Default.Comment,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Gears(gears: Map<String, String>) {
    LazyVerticalGrid(
        modifier = Modifier.width((75 * 2 + 5).dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        gears.map { (_, value) -> item {
            Image(url = value, modifier = Modifier.size(75.dp))
        } }
    }
}