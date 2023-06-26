package com.bulkapedia.compose.screens.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import com.bulkapedia.compose.elements.sheets.ClosableModalBottomSheet
import com.bulkapedia.compose.screens.titled.ScreenView

@Composable
fun TopScreen(hero: String, viewModel: TopViewModel = hiltViewModel()) {
    // store
    val store = DataStore(LocalContext.current)
    val nickname by store.getNickname.collectAsState("")
    val sets = viewModel.sets
    val currentSet by viewModel.setFlow.collectAsState()

    ScreenView(title = "Топ 100", showBack = true) {
        ClosableModalBottomSheet(
            sheetContent = {
                when (val set = currentSet) {
                    null -> HCenteredBox { Text(text = "") }
                    else -> SetTabCard(set, set.from != nickname) {}
                }
            }, currentSet, viewModel::closeSet
        ) {
            CenteredBox {
                TopSets(sets) { viewModel.listenSet(it.id) }
            }
        }
    }
    DisposableEffect(null) {
        viewModel.fetchSets(hero)
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun TopSets(sets: SnapshotStateList<UserSet>, onClick: (UserSet) -> Unit) {
    OutlinedCard(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
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
