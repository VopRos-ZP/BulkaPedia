package com.vopros.bulkapedia.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.CenterBox
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.userSet.UserSetCard
import com.vopros.bulkapedia.ui.screens.Screen
import com.vopros.bulkapedia.user.User
import com.vopros.bulkapedia.userSet.UserSetUseCase
import com.vopros.bulkapedia.userSet.UserSetWithUser

@Composable
fun ProfileScreen(userId: String) {
    val profile = stringResource(R.string.profile)
    var title by remember { mutableStateOf(profile) }
    Screen<Pair<User, List<UserSetUseCase>>, ProfileViewModel>(
        title = title,
        fetch = { startIntent(ProfileViewIntent.Start(userId)) },
        dispose = { startIntent(ProfileViewIntent.Dispose) }
    ) { _, (user, sets) ->
        LaunchedEffect(user) { title = user.nickname }
        // mains
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            when (sets) {
                emptyList<UserSetWithUser>() -> CenterBox { Text(R.string.empty_sets) }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(sets) {
                            HCenterBox { UserSetCard(it, true) }
                        }
                    }
                }
            }
        }
    }
}