package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.R
import com.bulkapedia.compose.data.labels.Stats

@Composable
fun SetSettingsMenu(
    expanded: MutableState<Boolean>,
    set: UserSet,
    onEditClick: (UserSet) -> Unit,
    onDeleteClick: (UserSet) -> Unit
) {
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20.dp))
    ) {
        DropdownMenu(
            modifier = Modifier
                .background(PrimaryDark)
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(4.dp),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownSetMenuItem(expanded, R.drawable.edit, "Редактировать") {
                onEditClick.invoke(set)
            }
            DropdownSetMenuItem(expanded, R.drawable.delete, "Удалить", Color.Red) {
                onDeleteClick.invoke(set)
            }
        }
    }
}

@Composable
fun DropdownSetMenuItem(
    expanded: MutableState<Boolean>,
    icon: Int,
    text: String,
    color: Color = Teal200,
    onClick: () -> Unit
) {
    DropdownMenuItem(onClick = { expanded.value = false; onClick.invoke() }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            tint = color,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(text = text, color = color)
    }
}

@Composable
fun MainTagSettingsMenu(
    expanded: MutableState<Boolean>,
    main: Pair<String, Stats>,
    onEditClick: (Pair<String, Stats>) -> Unit,
    onDeleteClick: (Pair<String, Stats>) -> Unit
) {
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20.dp))
    ) {
        DropdownMenu(
            modifier = Modifier
                .background(PrimaryDark)
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(4.dp),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownSetMenuItem(expanded, R.drawable.edit, "Редактировать") {
                onEditClick.invoke(main)
            }
            DropdownSetMenuItem(expanded, R.drawable.delete, "Удалить", Color.Red) {
                onDeleteClick.invoke(main)
            }
        }
    }
}

@Composable
fun ReceiveCommentsDropdownMenu(expanded: MutableState<Boolean>, onProfileClick: () -> Unit) {
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20.dp))
    ) {
        DropdownMenu(
            modifier = Modifier
                .background(PrimaryDark)
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(4.dp),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownSetMenuItem(expanded, R.drawable.person, "Профиль", onClick = onProfileClick)
        }
    }
}

@Composable
fun SendCommentsDropdownMenu(expanded: MutableState<Boolean>, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20.dp))
    ) {
        DropdownMenu(
            modifier = Modifier
                .background(PrimaryDark)
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(4.dp),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownSetMenuItem(expanded, R.drawable.edit, "Редактировать") {
                onEditClick.invoke()
            }
            DropdownSetMenuItem(expanded, R.drawable.delete, "Удалить", Color.Red) {
                onDeleteClick.invoke()
            }
        }
    }
}
