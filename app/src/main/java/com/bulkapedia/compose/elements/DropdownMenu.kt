@file:Suppress("FunctionName")

package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.R
import com.bulkapedia.compose.ui.theme.Primary
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
            DropdownMenuItem(onClick = { expanded.value = false; onEditClick.invoke(set) }) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "edit",
                    tint = Teal200,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = "Редактировать",
                    color = Teal200
                )
            }
            DropdownMenuItem(onClick = { expanded.value = false; onDeleteClick.invoke(set) }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete",
                    tint = Teal200,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = "Удалить",
                    color = Teal200
                )
            }
        }
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
            DropdownMenuItem(onClick = { expanded.value = false; onEditClick.invoke(main) }) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "edit",
                    tint = Teal200,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = "Редактировать",
                    color = Teal200
                )
            }
            DropdownMenuItem(onClick = { expanded.value = false; onDeleteClick.invoke(main) }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete",
                    tint = Teal200,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = "Удалить",
                    color = Teal200
                )
            }
        }
    }
}
