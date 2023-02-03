@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.top

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.R
import com.bulkapedia.compose.elements.ConfirmDialog
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.comments.CommentsViewModel
import com.bulkapedia.compose.screens.sets.DarkButton
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.DataStore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun TopScreen(
    ctx: ToolbarCtx,
    hero: String,
    viewModel: TopViewModel
) {
    ctx.observeAsState()
    ctx.setData("Топ 100", true)
    // store
    val store = DataStore(LocalContext.current)
    val nickname by store.getNickname.collectAsState(initial = "")

    val showSetDialog = remember { mutableStateOf(false) }
    val setState = viewModel.setState.observeAsState()
    val showErrorDialog = remember { mutableStateOf(false) }
    val showConfirmDialog = remember { mutableStateOf(false) }
    val setConfirmDialog = remember { mutableStateOf<UserSet?>(null) }
    val errorMessage = remember { mutableStateOf("") }
    val setsState = remember { mutableStateListOf<UserSet>() }
    // Select UI
    val viewState = viewModel.liveData.observeAsState()
    when (val state = viewState.value!!) {
        is TopViewState.EnterSets -> {
            TopSets(
                state.sets,
                onClickSet = {
                    showSetDialog.value = true
                    viewModel.setState.postValue(it)
                }
            )
        }
        is TopViewState.Error -> {
            errorMessage.value = state.message
            showErrorDialog.value = true
        }
        else -> {}
    }
    DisposableEffect(setsState) {
        if (setsState.isNotEmpty() && setState.value != CommentsViewModel.emptySet) {
            viewModel.listenSet(setState.value!!.userSetId)
        }
        onDispose { viewModel.removeListener() }
    }
    if (showErrorDialog.value) {
        TopError(errorMessage.value, showErrorDialog) {
            viewModel.obtainEvent(TopEvent.LoadingSets(hero, setsState))
        }
    }
    if (showSetDialog.value && setState.value != null) {
        Dialog({ /* on touch outside */ }) {
            Column(
                Modifier.fillMaxWidth()
                    .fillMaxHeight(fraction = 0.7f)
                    .background(PrimaryDark, RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SetTabCard(setState.value!!, setState.value!!.from != nickname) { s ->
                    setConfirmDialog.value = s
                    showConfirmDialog.value = true
                }
                HCenteredBox {
                    OutlinedButton(
                        onClick = { showSetDialog.value = false },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                        border = BorderStroke(2.dp, Color.Red)
                    ) { Text(text = "Закрыть", color = Color.Red) }
                }
            }
        }
    }
    if (showConfirmDialog.value && setConfirmDialog.value != null) {
        ConfirmDialog(showConfirmDialog,
            "Удалить сет", "Вы действительно хотите удалить сет?"
        ) {
            val set = setConfirmDialog.value!!
            val fs = Firebase.firestore
            fs.collection("sets").document(set.userSetId).delete().addOnSuccessListener {
                fs.collection("comments").whereEqualTo("set", set.userSetId)
                    .get().addOnSuccessListener { q ->
                        q.documents.forEach { d -> d.reference.delete() }
                    }
            }
        }
    }
    LaunchedEffect(key1 = viewState) {
        viewModel.obtainEvent(TopEvent.LoadingSets(hero, setsState))
    }
}

@Composable
fun TopSets(
    state: SnapshotStateList<UserSet>,
    onClickSet: (UserSet) -> Unit
) {
    CenteredBox(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.923f)
            .padding(20.dp)
            .background(PrimaryDark, RoundedCornerShape(25.dp))
            .border(2.dp, Teal200, RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp))
            .padding(horizontal = 10.dp)
    ) {
        LazyColumn {
            itemsIndexed(state) { i, set ->
                TopItem(i, set,
                    state.last().userSetId == set.userSetId, onClickSet
                )
            }
        }
    }
}

@Composable
fun TopItem(
    i: Int, set: UserSet, isLast: Boolean,
    onClickSet: (UserSet) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (isLast) 0.dp else 5.dp)
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
            fontSize = 14.sp,
            modifier = Modifier.padding(end = 20.dp)
        )
        DarkButton(id = R.drawable.book, Modifier.padding(start = 10.dp)) {
            onClickSet.invoke(set)
        }
    }
}

@Composable
fun TopError(msg: String, showDialog: MutableState<Boolean>, onClickDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { showDialog.value = false; onClickDismiss.invoke() },
        confirmButton = {
            OutlinedButton(
                onClick = { showDialog.value = false },
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(2.dp, Teal200)
            ) { Text(text = "Закрыть и повторить попытку", color = Teal200) }
        },
        title = { Text(
            text = "Ошибка",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp) },
        text = { HCenteredBox { Text(text = msg, color = Teal) } },
        shape = RoundedCornerShape(25.dp)
    )
}
