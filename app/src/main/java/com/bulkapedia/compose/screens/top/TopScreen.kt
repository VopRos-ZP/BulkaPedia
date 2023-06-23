package com.bulkapedia.compose.screens.top

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.R
import com.bulkapedia.compose.screens.sets.DarkButton
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.screens.titled.ScreenView

@Composable
fun TopScreen(hero: String, viewModel: TopViewModel = hiltViewModel()) {
    // store
    val store = DataStore(LocalContext.current)
    val nickname by store.getNickname.collectAsState("")
    val sets by viewModel.setsFlow.collectAsState()
    val currentSetState = viewModel.setFlow.collectAsState()

    ScreenView(title = "Топ 100", showBack = true) {
        CenteredBox {
            TopSets(sets) { viewModel.listenSet(it.id) }
            when (val set = currentSetState.value) {
                null -> {}
                else -> ShowTopSet(set, nickname, viewModel::closeSet)
            }
        }
    }
    DisposableEffect(null) {
        viewModel.fetchSets(hero)
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun TopSets(sets: List<UserSet>, onClick: (UserSet) -> Unit) {
    OutlinedCard {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsIndexed(sets) { i, set ->
                TopItem(i, set) { onClick(set) }
            }
        }
    }
}

@Composable
fun TopItem(i: Int, set: UserSet, onClick: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent, RoundedCornerShape(15.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${i + 1}",
            color = Teal200,
            fontSize = 14.sp,
            modifier = Modifier.padding(end = 20.dp)
        )
        Text(
            text = set.from,
            color = Teal200,
            fontSize = 12.sp,
            modifier = Modifier.padding(end = 20.dp)
        )
        DarkButton(id = R.drawable.book, Modifier.padding(start = 10.dp), onClick)
    }
}

@Composable
fun ShowTopSet(set: UserSet, nickname: String?, onClose: () -> Unit) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SetTabCard(set, set.from != nickname) {}
            HCenteredBox {
                OutlinedButton(
                    onClick = onClose,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                    border = BorderStroke(2.dp, Color.Red)
                ) { Text(text = "Закрыть", color = Color.Red) }
            }
        }
    }
}
